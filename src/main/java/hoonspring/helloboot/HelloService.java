package hoonspring.helloboot;

import org.springframework.stereotype.Service;

/*
 * @Service는 @Component를 메타 어노테이션으로 가지고 있음.
 * ※ Meta-Annotation : 어노테이션의 위에 붙은 어노테이션.
 */
@Service
public class HelloService implements HelloServiceInf {
	
	private final HelloRepository helloRepository;
	
	public HelloService(HelloRepository helloRepository) {
		this.helloRepository = helloRepository;
	}
	
	
	@Override
	public String sayHello(String name) {
		this.helloRepository.increaseCount(name);
		
		return "Hello " + name;
	}
	
	@Override
	public int countOf(String count) {
		return helloRepository.countOf(count);
	}
}
