package how;

import com.devexperts.rmi.RMIEndpoint;
import com.devexperts.rmi.RMIServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");

        RMIEndpoint ENDPOINT = RMIEndpoint.createEndpoint(RMIEndpoint.Side.SERVER);
        RMIServer rmiServer = ENDPOINT.getServer();
        ENDPOINT.connect(":11551");
        Thread.sleep(Long.MAX_VALUE);
    }
}