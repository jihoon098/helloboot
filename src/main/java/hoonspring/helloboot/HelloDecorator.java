package hoonspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/*
 * Decorator 패턴: 기존 오브젝트의 코드를 수정하지 않고 어떤 기능을 추가할 때 구현하는 것을 데코레이터 라고 부름
 */
@Service
@Primary // 의존주입의 대상이 다수일때 우선순위를 부여
public class HelloDecorator implements HelloServiceInf {
	
	private HelloServiceInf helloServiceInf;
	
	public HelloDecorator(HelloServiceInf helloServiceinf) {
		this.helloServiceInf = helloServiceinf;
	}
	
	@Override
	public String sayHello(String name) {
		return "*" + helloServiceInf.sayHello(name) + "*";
	}
	
	@Override
	public int countOf(String count) {
		return helloServiceInf.countOf(count);
	}
}
