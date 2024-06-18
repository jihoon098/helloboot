package hoonspring.helloboot;

public class HelloService implements HelloServiceInf {
	
	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
