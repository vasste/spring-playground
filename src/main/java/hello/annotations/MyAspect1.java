package hello.annotations;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Vasilii Stepanov.
 * @since 20.06.2018
 */
@Aspect
public class MyAspect1 {

    @Pointcut("@annotation(hello.annotations.AspectAnnotation)")
    public void annotated() {}

    @Before("annotated()")
    public void printABit() {
        System.out.println("Aspect1");
    }
}
