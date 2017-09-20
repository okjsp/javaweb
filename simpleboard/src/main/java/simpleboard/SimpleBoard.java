package simpleboard;

import java.util.ArrayList;
import java.util.List;

public class SimpleBoard {
    List<Article> store = new ArrayList<Article>();

    public boolean add(Article article) {
        store.add(article);
        return true;
    }

    public Article get(long l) {
        for (Article article:store) {
            if (article.getId() == l) {
                try {
                    return (Article) article.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public long size() {
        return store.size();
    }

    public boolean delete(long l) {

        for (Article article:store) {
            if (article.getId() == l) {
                store.remove(article);
                return true;
            }
        }
        return false;
    }

    public void update(Article article2) {
        for (Article article:store) {
            if (article.getId() == article2.getId()) {
                int idx = store.indexOf(article);
                store.remove(article);
                store.add(idx, article2);
            }
        }

    }

    public List<Article> getList() {
        return store;
    }
}
