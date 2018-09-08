package howto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BSeviceImpl implements BService {

    @Autowired
    private BDAO dao;

    @Autowired
    private AService aService;

    @Override
    @Transactional
    public B createB(B b) {
        A a = aService.get("test");
        return dao.createB(b);
    }

    @Override
    @Transactional
    public List<B> readBs() {
        return dao.getBs().stream().map(bb -> bb.build(aService)).collect(Collectors.toList());
    }
}
