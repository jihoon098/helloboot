package hoonspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
	
	private final class helloRepositoryStub implements HelloRepository {
		@Override
		public void increaseCount(String name) {
		}

		@Override
		public Hello findHello(String name) {
			return null;
		}
	}
	
	@Test
	void helloService() {
		HelloService helloService = new HelloService(new helloRepositoryStub());
		
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
