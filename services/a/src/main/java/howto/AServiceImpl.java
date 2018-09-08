package howto;

import org.springframework.stereotype.Service;

@Service
public class AServiceImpl implements AService {

    @Override
    public A get(String text) {
        return new A(text);
    }
}
