package simpleboard;

import java.util.List;

public class SimpleBoard {
    SimpleBoardDao dao = new SimpleBoardDao();

    public int add(Article article) {
        int add = dao.add(article);
        return add;
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
