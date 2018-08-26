package hello.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Vasilii Stepanov.
 * @since 20.06.2018
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AspectAnnotation {
}
