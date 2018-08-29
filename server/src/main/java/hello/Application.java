package hello;

import com.devexperts.rmi.RMIEndpoint;
import com.devexperts.rmi.RMIServer;
import com.devexperts.rmi.RMIServiceInterface;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author Vasilii Stepanov.
 * @since 19.06.2018
 */
public class Application implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    /**
     * This method is called from within the ApplicationContext once it is
     * done starting up, it will stick a reference to itself into this bean.
     * @param context a reference to the ApplicationContext.
     */
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        CONTEXT = context;
    }

    public static <C> C getBean(Class<C> beanClass) {
        return CONTEXT.getBean(beanClass);
    }

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");

        RMIEndpoint ENDPOINT = RMIEndpoint.createEndpoint(RMIEndpoint.Side.SERVER);
        RMIServer rmiServer = ENDPOINT.getServer();
        Reflections ref = new Reflections("hello");
        for (Class clazz : ref.getTypesAnnotatedWith(RMIServiceInterface.class)) {
            if (clazz.isInterface())
                rmiServer.export(ctx.getBean(clazz), clazz);
        }
        ENDPOINT.connect(":11555");
        Thread.sleep(Long.MAX_VALUE);
    }
}