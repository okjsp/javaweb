package spring.guide.helloworld;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

@Configuration
@ComponentScan
public class HelloServiceTest {

    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext(HelloServiceTest.class);
    }

    @Test
    public void testSayHello() {
        HelloService hello = context.getBean(HelloService.class);
        assertEquals("Hello devcamp!!!", hello.sayHello());
    }

}