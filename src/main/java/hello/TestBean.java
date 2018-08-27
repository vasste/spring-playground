package hello;

import com.devexperts.rmi.RMIServiceInterface;
import com.devexperts.rmi.task.RMIChannelSupport;

import java.util.List;

/**
 * @author Vasilii Stepanov.
 * @since 26.06.2018
 */
@RMIServiceInterface
public interface TestBean extends RMIChannelSupport {
    void add(List<Foo> foos);
    List<Foo> read();
    void save();
}
