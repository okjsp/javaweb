package simpleboard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class JdbcTest {

    private Connection conn;

    @Test
    public void getConnection() {
        assertNotNull(conn);
    }

    @Before
    public void setUp() {
        String sql = "CREATE TABLE USER (ID INT, NAME VARCHAR(50));";
        execute(sql);
    }

    @After
    public void tearDown() {
        String sql = "DROP TABLE USER;";
        execute(sql);
    }

    private void execute(String sql) {
        conn = Jdbc.getConnection();

        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUser() {
        long id = 1L;
        String name = "kenu";
        Jdbc.addUser(id, name);

        String sqlselect = "SELECT * FROM USER WHERE ID = 1";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlselect);
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            String name2 = resultSet.getString(2);
            assertEquals(name, name2);
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}