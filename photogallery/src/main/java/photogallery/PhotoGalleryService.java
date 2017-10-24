package photogallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoGalleryService {

    @Autowired
    PhotoGalleryDao photoGalleryDao;

    public long add(Article article) {
        return photoGalleryDao.add(article);
    }

    public Article get(long id) {
        return photoGalleryDao.getById(id);
    }

    public long size() {
        return photoGalleryDao.size();
    }

    public boolean delete(long id) {
        return photoGalleryDao.delete(id);
    }

    public void update(Article article) {
        photoGalleryDao.update(article);
    }

    public List<Article> getList() {
        return photoGalleryDao.getList();
    }
}
