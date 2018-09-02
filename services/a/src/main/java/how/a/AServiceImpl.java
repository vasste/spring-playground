package how.a;

import how.remote.RaService;
import org.springframework.beans.factory.annotation.Autowired;

public class AServiceImpl implements AService {

    @Autowired
    RaService raService;

    @Override
    public A get(String text) {

        return null;
    }
}
