package photogallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PhotoGalleryDao {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public int add(Article article) {
        Connection conn = getConnection();
        PreparedStatement pstmt;
        try {
            String sql = "INSERT INTO photogallery (writer, title, content, filename, savename, filesize)" +
                    " VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getWriter());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContent());
            pstmt.setString(4, article.getFilename());
            pstmt.setString(5, article.getSaveName());
            pstmt.setLong(6, article.getFileSize());
            int i = pstmt.executeUpdate();
            pstmt.close();
            if (i == 1) {
                pstmt = conn.prepareStatement("SELECT max(id) FROM photogallery");
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    i = resultSet.getInt(1);
                }
                resultSet.close();
                pstmt.close();
                conn.close();
            }
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Article getById(long id) {
        Connection conn = getConnection();
        String sql = "SELECT * FROM photogallery WHERE id = ?";
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
                article.setFilename(rs.getString("filename"));
                article.setSaveName(rs.getString("savename"));
                article.setFileSize(rs.getLong("filesize"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return article;
    }

    public long size() {
        Connection conn = getConnection();
        String sql = "SELECT count(*) FROM photogallery";
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
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean delete(long id) {
        Connection conn = getConnection();
        String sql = "DELETE from photogallery WHERE id = ?";
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
        Connection conn = getConnection();
        String sql = "UPDATE photogallery SET" +
                " writer =?, title = ?, content = ?," +
                " filename = ?, savename = ?, filesize = ?" +
                " where id = ?";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getWriter());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContent());
            pstmt.setString(4, article.getFilename());
            pstmt.setString(5, article.getSaveName());
            pstmt.setLong(6, article.getFileSize());
            pstmt.setLong(7, article.getId());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Article> getList() {

        Connection conn = getConnection();
        List<Article> list = new ArrayList<Article>();
        String sql = "SELECT * FROM photogallery ORDER by id desc";
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
                article.setFilename(rs.getString("filename"));
                article.setSaveName(rs.getString("savename"));
                article.setFileSize(rs.getLong("filesize"));
                list.add(article);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }
}
