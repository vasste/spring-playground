package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Vasilii Stepanov.
 * @since 20.06.2018
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class MyTests {

    @Autowired
    TestBean bean;

    @Test
    public void name() {
        bean.add(Arrays.asList(new Foo(45)));
    }
}
