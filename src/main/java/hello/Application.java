package hello;

import com.devexperts.rmi.RMIEndpoint;
import com.devexperts.rmi.RMIServer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;

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

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");

        RMIEndpoint ENDPOINT = RMIEndpoint.createEndpoint(RMIEndpoint.Side.SERVER);
        RMIServer rmiServer = ENDPOINT.getServer();
        rmiServer.export(ctx.getBean(TestBean.class), TestBean.class);

        ctx.getBean(TestBean.class).add(Collections.emptyList());

        ENDPOINT.connect(":11555");
        Thread.sleep(Long.MAX_VALUE);
    }
}