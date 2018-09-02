package how.annotations;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Vasilii Stepanov.
 * @since 20.06.2018
 */
@Aspect
public class MyAspect1 {

    @Pointcut("@annotation(how.annotations.AspectAnnotation)")
    public void annotated() {}

    @Before("annotated()")
    public void printABit() {
        System.out.println("Aspect1");
    }
}
