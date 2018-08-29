package hello.dao;

import hello.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Vasilii Stepanov.
 * @since 19.06.2018
 */
@Component
public class TestDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(TransactionAwareDataSourceProxy dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Foo> getFoos() {
        return jdbcTemplate.query("select id from test", new ResultSetExtractor<List<Foo>>() {
            @Override
            public List<Foo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Foo> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new Foo(rs.getInt("id")));
                }
                return list;
            }
        });
    }

    public void createFoo(List<Foo> foos) {
        for (Foo foo : foos) {
            jdbcTemplate.update("insert into test (id, name) values (NEXTVAL('SEQ_ID'), ?)", foo.getIndex());
        }
    }
}
