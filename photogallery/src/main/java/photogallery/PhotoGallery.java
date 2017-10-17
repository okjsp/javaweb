package photogallery;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhotoGallery {
    PhotoGalleryDao dao = new PhotoGalleryDao();

    public int add(Article article) {
        return dao.add(article);
    }

    public Article get(long id) {
        return dao.getById(id);
    }

    public long size() {
        return dao.size();
    }

    public boolean delete(long id) {
        return dao.delete(id);
    }

    public void update(Article article) {
        dao.update(article);
    }

    public List<Article> getList() {
        return dao.getList();
    }
}
