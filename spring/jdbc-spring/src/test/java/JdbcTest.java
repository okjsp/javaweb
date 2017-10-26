import jdbc.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.assertEquals;

public class JdbcTest {
    ApplicationContext context;
    UserDao userDao;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("Beans.xml");
        userDao = (UserDao) context.getBean("UserDao");

        String sql = "CREATE TABLE USER (ID INT, NAME VARCHAR(50));";
        execute(sql);
    }

    @After
    public void tearDown() {
        String sql = "DROP TABLE USER;";
        execute(sql);
    }

    private void execute(String sql) {
        JdbcTemplate jdbcTemplateObject = userDao.getJdbcTemplateObject();
        jdbcTemplateObject.execute(sql);
    }

    @Test
    public void addUser() {
        long id = 1L;
        String name = "kenu";
        userDao.addUser(id, name);
        String name2 = userDao.getUserNameById(id);
        assertEquals(name, name2);
    }
}
