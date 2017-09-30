package simpleboard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleBoardDao {

    private static Connection conn;
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/devcamp", "javauser", "javadude");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int add(Article article) {
        String sql = "INSERT INTO simpleboard (writer, title, content) VALUES (?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getWriter());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContent());
            int i = pstmt.executeUpdate();
            pstmt.close();
            if (i == 1) {
                pstmt = conn.prepareStatement("SELECT max(id) FROM simpleboard");
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    i = resultSet.getInt(1);
                }
                resultSet.close();
                pstmt.close();
            }
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Article getById(long id) {
        String sql = "SELECT * FROM simpleboard WHERE id = ?";
        PreparedStatement pstmt;
        ResultSet rs;
        Article article = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                article = new Article();
                article.setId(rs.getLong("id"));
                article.setWriter(rs.getString("writer"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return article;
    }

    public long size() {
        String sql = "SELECT count(*) FROM simpleboard";
        PreparedStatement pstmt;
        ResultSet rs;
        Long count = 0L;

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean delete(long id) {
        String sql = "DELETE from simpleboard WHERE id = ?";
        PreparedStatement pstmt;
        int cnt = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            cnt = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnt == 1;
    }

    public void update(Article article) {
        String sql = "UPDATE simpleboard SET writer =?, title = ?, content = ? where id = ?";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getWriter());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContent());
            pstmt.setLong(4, article.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Article> getList() {

        List<Article> list = new ArrayList<Article>();
        String sql = "SELECT * FROM simpleboard ORDER by id desc";
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getLong("id"));
                article.setWriter(rs.getString("writer"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                list.add(article);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }
}
