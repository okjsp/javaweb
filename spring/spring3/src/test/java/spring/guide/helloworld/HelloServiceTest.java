package spring.guide.helloworld;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {

    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        String configLocation = "testContext.xml";
        context = new ClassPathXmlApplicationContext(configLocation);
    }

    @Test
    public void testSayHello() {
        HelloService helloworld = (HelloService)context.getBean("hello");
        assertEquals( "Hello devcamp test!!!", helloworld.sayHello() );
    }

}