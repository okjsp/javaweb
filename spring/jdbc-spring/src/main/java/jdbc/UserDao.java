package jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

public class UserDao {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplateObject;

    public JdbcTemplate getJdbcTemplateObject() {
        return jdbcTemplateObject;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void addUser(long id, String name) {
        String sql = "INSERT INTO USER (ID, NAME) VALUES (?, ?)";
        jdbcTemplateObject.update(sql, id, name);
    }

    public String getUserNameById(long id) {
        String sql = "SELECT * FROM USER WHERE ID = ?";
        Map<String, Object> map = jdbcTemplateObject.queryForMap(sql, new Long(id));
        String name = (String)map.get("NAME");
        return name;
    }
}
