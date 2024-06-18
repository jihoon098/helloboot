package hoonspring.helloboot;

import java.util.Objects;

public class HelloController {
	// final은 정의할 때부터 초기화 or 생성자에서 초기화 코드가 필요
	private final HelloServiceInf helloServiceInf;
	
	public HelloController(HelloServiceInf helloServiceInf) {
		this.helloServiceInf = helloServiceInf;
	}
	
	public String hello(String name) {
		
		/*
		 * Objects.requireNonNull <- Null 체크를 위한 메소드
		 * 파라미터로 입력된 값이 null 이라면 NPE(NullPointerException)가 발생
		 * not null 이면 입력값을 그대로 반환함
		 */
		return helloServiceInf.sayHello(Objects.requireNonNull(name));
	}
}
