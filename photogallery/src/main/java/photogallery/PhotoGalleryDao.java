package photogallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class PhotoGalleryDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public long add(Article article) {
        String sql = "INSERT INTO photogallery (writer, title, content, filename, savename, filesize)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        int update = jdbcTemplate.update(sql, new Object[]{article.getWriter(),
                article.getTitle(), article.getContent(),
                article.getFilename(), article.getSaveName(),
                article.getFileSize()
        });

        long cnt = 0;
        if (update == 1) {
            cnt = jdbcTemplate.queryForObject("SELECT max(id) FROM photogallery", Long.class);
        }
        return cnt;
    }

    public Article getById(long id) {
        String sql = "SELECT * FROM photogallery WHERE id = ?";
        Article article = jdbcTemplate.queryForObject(sql, new ArticleMapper(), id);

        return article;
    }

    public long size() {
        String sql = "SELECT count(*) FROM photogallery";
        long cnt = jdbcTemplate.queryForObject(sql, Long.class);
        return cnt;
    }

    public boolean delete(long id) {
        String sql = "DELETE from photogallery WHERE id = ?";
        int update = jdbcTemplate.update(sql, id);
        return update == 1;
    }

    public void update(Article article) {
        String sql = "UPDATE photogallery SET" +
                " writer =?, title = ?, content = ?," +
                " filename = ?, savename = ?, filesize = ?" +
                " where id = ?";
        jdbcTemplate.update(sql, new Object[] {article.getWriter(), article.getTitle(),
                article.getContent(), article.getFilename(),
                article.getSaveName(), article.getFileSize(),
                article.getId()});
    }

    public List<Article> getList() {
        String sql = "SELECT * FROM photogallery ORDER by id desc";
        return jdbcTemplate.query(sql, new ArticleMapper());
    }

    protected static final class ArticleMapper implements RowMapper<Article> {
        public Article mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setWriter(rs.getString("writer"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setFilename(rs.getString("filename"));
            article.setSaveName(rs.getString("savename"));
            article.setFileSize(rs.getLong("filesize"));
            return article;
        }
    }

}
