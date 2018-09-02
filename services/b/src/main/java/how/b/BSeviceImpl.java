package how.b;

import com.devexperts.rmi.task.RMIChannelSupport;
import com.devexperts.rmi.task.RMITask;
import how.a.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class BSeviceImpl implements BService, RMIChannelSupport {

    @Autowired
    private BDAO dao;

    @Autowired
    private AService aService;

    @Override
    @Transactional
    public B createB(B b) {
        return dao.createB(b);
    }

    @Override
    @Transactional
    public List<B> readBs() {
        return dao.getBs().stream().map(bb -> bb.build(aService)).collect(Collectors.toList());
    }

// RMI channel impl
//    public List<Foo> read() {
//        List<Foo> list = dao.getFoos();
//        RMITask task = RMITask.current();
//        try {
//            RMIOperation<ServerClient> opr =
//                    RMIOperation.valueOf(ServerClient.class,
//                            ServerClient.class.getMethod("send", Foo.class));
//            //while (true) {
//            RMIRequest<ServerClient> req = task.getChannel().
//                    createRequest(RMIRequestType.ONE_WAY, opr, list.iterator().next());
//            req.send();
//            while (req.isCompleted());
//            //}
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        return Collections.emptyList();
//    }


    @Override
    public void openChannel(RMITask task) {

    }
}
