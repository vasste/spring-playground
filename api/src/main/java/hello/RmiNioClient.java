package hello;

import com.devexperts.rmi.RMIClient;
import com.devexperts.rmi.RMIEndpoint;
import com.devexperts.rmi.RMIOperation;
import com.devexperts.rmi.RMIRequest;
import com.devexperts.rmi.message.RMIRequestMessage;
import com.devexperts.rmi.message.RMIRequestType;
import com.devexperts.rmi.security.SecurityContext;
import com.dxfeed.promise.Promise;
import com.dxfeed.promise.PromiseHandler;
import hello.Foo;
import hello.TestBean;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Vasilii Stepanov.
 * @since 27.02.2018
 */
public class RmiNioClient {
    public static void main(String[] args) throws RemoteException, NoSuchMethodException, InterruptedException {
        RMIEndpoint endpoint = RMIEndpoint.createEndpoint(RMIEndpoint.Side.CLIENT);
        SecurityContext.getInstance().setSubject("test2");
        endpoint.connect("localhost:11555");
        RMIClient rmiClient = endpoint.getClient();
        RMIRequestMessage<List<Foo>> rmiMessage = new RMIRequestMessage<>(RMIRequestType.DEFAULT,
                RMIOperation.valueOf(TestBean.class, TestBean.class.getMethod("read")));
        RMIRequest<List<Foo>> req = rmiClient.createRequest(rmiMessage);
        TestBean proxy = rmiClient.getProxy(TestBean.class);
       // proxy.add(Collections.singletonList(new Foo(1)));
        req.getChannel().addChannelHandler(new ServerClient() {
            @Override
            public void send(Foo foo) {
                System.out.println(foo.getIndex());
            }
        }, ServerClient.class);
        req.getPromise().whenDone(new PromiseHandler<List<Foo>>() {
            @Override
            public void promiseDone(Promise<? extends List<Foo>> promise) {
                System.out.println("sdsd");
            }
        });
        req.send();
        Thread.sleep(Long.MAX_VALUE);
        //proxy.add(new ArrayList<>(Arrays.asList(new Foo(1), new Foo(2))));
        //System.out.println(proxy.read());
    }
}
