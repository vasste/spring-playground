package hello;

import com.devexperts.rmi.RMIOperation;
import com.devexperts.rmi.RMIRequest;
import com.devexperts.rmi.message.RMIRequestType;
import com.devexperts.rmi.task.RMIChannelSupport;
import com.devexperts.rmi.task.RMITask;
import hello.annotations.AspectAnnotation;
import hello.dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Collections;
import java.util.List;

/**
 * @author Vasilii Stepanov.
 * @since 20.06.2018
 */
@Component
public class TestBeanService implements TestBean, RMIChannelSupport {

    @Autowired
    private TestDAO dao;

    @Transactional
    public List<Foo> read() {
        List<Foo> list = dao.getFoos();
        RMITask task = RMITask.current();
        try {
            RMIOperation<ServerClient> opr =
                    RMIOperation.valueOf(ServerClient.class,
                            ServerClient.class.getMethod("send", Foo.class));
            //while (true) {
                RMIRequest<ServerClient> req = task.getChannel().
                        createRequest(RMIRequestType.ONE_WAY, opr, list.iterator().next());
                req.send();
                while (req.isCompleted());
            //}
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @AspectAnnotation
    @Transactional
    public void add(List<Foo> foos) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void beforeCommit(boolean readOnly) {
//                TransactionStatus transactionStatus = TransactionInterceptor.currentTransactionStatus();
//                transactionStatus.setRollbackOnly();
                dao.createFoo(Collections.singletonList(new Foo(185)));
//                if (TransactionSynchronizationManager.isActualTransactionActive()) {
                    //TransactionSynchronizationManager.setCurrentTransactionReadOnly(true);
//                }
            }

            @Override
            public void afterCommit() {
                //dao.createFoo(Collections.singletonList(new Foo(102)));
                super.afterCommit();
            }
        });
        dao.createFoo(foos);
    }

    @AspectAnnotation
    @Transactional
    public void save() {
//        repository.save(new ACond(-9, "Hell9"));
//        repository.save(new ACond(-10, "Hell10"));
//        repository.findAndStream(8).forEach(out::println);
    }

    @Override
    public void openChannel(RMITask task) {
    }
}
