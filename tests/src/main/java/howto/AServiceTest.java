package howto;

import org.springframework.stereotype.Service;

/**
 * @author Vasilii Stepanov.
 * @since 06.09.2018
 */
@Service
public class AServiceTest implements AService {
    @Override
    public A get(String text) {
        System.out.println("AServiceTest");
        return new A(text);
    }
}

