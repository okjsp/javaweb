package simpleboard;

import java.util.List;

import junit.framework.TestCase;

public class SimpleBoardTest extends TestCase {
    public void testAdd() {
        Article article = new Article();
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        SimpleBoard simpleBoard = new SimpleBoard();
        int add = simpleBoard.add(article);
        assertTrue(add > 0);
    }

    public void testGet() {
        Article article = new Article();
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        SimpleBoard simpleBoard = new SimpleBoard();
        int add = simpleBoard.add(article);
        assertTrue(add > 0);

        Article article2 = simpleBoard.get(add);
        assertEquals(article.getWriter(), article2.getWriter());

    }

    public void testDelete() {
        Article article = new Article();
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        SimpleBoard simpleBoard = new SimpleBoard();
        int add = simpleBoard.add(article);

        long sizeBefore = simpleBoard.size();
        boolean result = simpleBoard.delete(add);
        assertTrue(result);
        long sizeAfter = simpleBoard.size();
        assertEquals(1, sizeBefore - sizeAfter);
    }

    public void testUpdate() {
        Article article = new Article();
        article.setId(1L);
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        SimpleBoard simpleBoard = new SimpleBoard();
        int add = simpleBoard.add(article);
        assertTrue(add > 0);

        Article article2 = simpleBoard.get(add);
        article2.setContent("content changed");
        simpleBoard.update(article2);

        Article article3 = simpleBoard.get(add);
        assertNotSame(article2, article3);
        assertEquals("content changed", article3.getContent());

    }

    public void testGetList() {
        Article article = new Article();
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        Article article2 = new Article();
        article2.setWriter("kenu2");
        article2.setTitle("title2");
        article2.setContent("content2");

        SimpleBoard simpleBoard = new SimpleBoard();
        int before = simpleBoard.getList().size();
        int add = simpleBoard.add(article);
        assertTrue(add > 0);
        int add1 = simpleBoard.add(article2);
        assertTrue(add1 > 0);

        List<Article> list = simpleBoard.getList();
        int size = list.size();
        assertEquals(2, size - before);

    }

}
