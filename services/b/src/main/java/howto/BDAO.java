package howto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class BDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<B.Builder> getBs() {
        return jdbcTemplate.query("select * from test", rs -> {
            List<B.Builder> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new B.Builder(rs.getLong("id"), rs.getString("name")));
            }
            return list;
        });
    }

    public B createB(B b) {
        long id = jdbcTemplate.query("select NEXTVAL('SEQ_ID')", rs -> { return rs.getLong(1); });
        jdbcTemplate.update("insert into test (id, name) values (?, ?)", id, b.getA().getText());
        return new B(b.getA(), id);
    }
}
