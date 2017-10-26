package photogallery;

import java.util.List;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:photogallery-test.xml")
public class PhotoGalleryServiceTest extends TestCase {
    @Autowired
    PhotoGalleryService photoGalleryService;

    @Test
    public void testAdd() {
        Article article = new Article();
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        long add = photoGalleryService.add(article);
        assertTrue(add > 0);
    }

    @Test
    public void testGet() {
        Article article = new Article();
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        long add = photoGalleryService.add(article);
        assertTrue(add > 0);

        Article article2 = photoGalleryService.get(add);
        assertEquals(article.getWriter(), article2.getWriter());

    }

    @Test
    public void testDelete() {
        Article article = new Article();
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        long add = photoGalleryService.add(article);

        long sizeBefore = photoGalleryService.size();
        boolean result = photoGalleryService.delete(add);
        assertTrue(result);
        long sizeAfter = photoGalleryService.size();
        assertEquals(1, sizeBefore - sizeAfter);
    }

    @Test
    public void testUpdate() {
        Article article = new Article();
        article.setId(1L);
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        long add = photoGalleryService.add(article);
        assertTrue(add > 0);

        Article article2 = photoGalleryService.get(add);
        article2.setContent("content changed");
        photoGalleryService.update(article2);

        Article article3 = photoGalleryService.get(add);
        assertNotSame(article2, article3);
        assertEquals("content changed", article3.getContent());

    }

    @Test
    public void testGetList() {
        Article article = new Article();
        article.setWriter("kenu");
        article.setTitle("title");
        article.setContent("content");

        Article article2 = new Article();
        article2.setWriter("kenu2");
        article2.setTitle("title2");
        article2.setContent("content2");

        int before = photoGalleryService.getList().size();
        long add = photoGalleryService.add(article);
        assertTrue(add > 0);
        long add1 = photoGalleryService.add(article2);
        assertTrue(add1 > 0);

        List<Article> list = photoGalleryService.getList();
        int size = list.size();
        assertEquals(2, size - before);

    }

    @Test
    public void testGetSize() {
        int size = photoGalleryService.getList().size();
        assertTrue(size > 0);
    }

}
