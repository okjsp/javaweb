package spring.guide.helloworld;

public class HelloServiceImpl implements HelloService{

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String sayHello() {
		return "Hello " + name + "!!!" ;
	}
}
