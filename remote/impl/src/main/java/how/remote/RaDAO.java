package how.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class RaDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public RA get(long id) {

    }

    public RA get(String text) {
        
    }
}
