package howto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Vasilii Stepanov.
 * @since 06.09.2018
 */
@ContextConfiguration(locations="/test-application-context.xml")
@RunWith(SpringRunner.class)
public class HowToTest {

    @Autowired
    private BService bService;

    @Autowired
    private AService aService;

    @Test
    public void firstTest() {
        A a = aService.get("test");
        B b = bService.createB(new B(a, 10));
        Assert.assertNotEquals(b.getId(), 10);
    }
}
