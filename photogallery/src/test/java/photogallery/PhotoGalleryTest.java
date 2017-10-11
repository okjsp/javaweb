package photogallery;

import java.util.List;

import junit.framework.TestCase;

public class PhotoGalleryTest extends TestCase {
    public void testAdd() {
        Article article = new Article();
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        PhotoGallery photoGallery = new PhotoGallery();
        int add = photoGallery.add(article);
        assertTrue(add > 0);
    }

    public void testGet() {
        Article article = new Article();
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        PhotoGallery photoGallery = new PhotoGallery();
        int add = photoGallery.add(article);
        assertTrue(add > 0);

        Article article2 = photoGallery.get(add);
        assertEquals(article.getWriter(), article2.getWriter());

    }

    public void testDelete() {
        Article article = new Article();
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        PhotoGallery photoGallery = new PhotoGallery();
        int add = photoGallery.add(article);

        long sizeBefore = photoGallery.size();
        boolean result = photoGallery.delete(add);
        assertTrue(result);
        long sizeAfter = photoGallery.size();
        assertEquals(1, sizeBefore - sizeAfter);
    }

    public void testUpdate() {
        Article article = new Article();
        article.setId(1L);
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        PhotoGallery photoGallery = new PhotoGallery();
        int add = photoGallery.add(article);
        assertTrue(add > 0);

        Article article2 = photoGallery.get(add);
        article2.setContent("content changed");
        photoGallery.update(article2);

        Article article3 = photoGallery.get(add);
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

        PhotoGallery photoGallery = new PhotoGallery();
        int before = photoGallery.getList().size();
        int add = photoGallery.add(article);
        assertTrue(add > 0);
        int add1 = photoGallery.add(article2);
        assertTrue(add1 > 0);

        List<Article> list = photoGallery.getList();
        int size = list.size();
        assertEquals(2, size - before);

    }

}
