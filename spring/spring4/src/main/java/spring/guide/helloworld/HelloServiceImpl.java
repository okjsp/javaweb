package spring.guide.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

	private String name;

	@Value("devcamp")
	public void setName(String name) {
		this.name = name;
	}

	public String sayHello() {
		return "Hello " + name + "!!!" ;
	}
}
