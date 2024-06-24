package hoonspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
	
	@Test
	void helloService() {
		HelloService helloService = new HelloService();
		
		String ret = helloService.sayHello("Spring");
		
		Assertions.assertThat(ret).isEqualTo("Hello Spring");
	}
	@Test
	void helloDecorator() {
		HelloDecorator decorator = new HelloDecorator(name -> name);
		
		String ret = decorator.sayHello("Test");
		
		Assertions.assertThat(ret).isEqualTo("*Test*");
	}
}
