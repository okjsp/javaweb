package spring.guide.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String configLocation = "applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		HelloService hello = (HelloService)context.getBean("hello");

		System.out.println(hello.sayHello());
	}

}
