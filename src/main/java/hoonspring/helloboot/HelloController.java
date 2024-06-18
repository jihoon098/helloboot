package hoonspring.helloboot;

import java.util.Objects;

public class HelloController {
	
	public String hello(String name) {
		HelloService helloService = new HelloService();
		
		/*
		 * Objects.requireNonNull <- Null 체크를 위한 메소드
		 * 파라미터로 입력된 값이 null 이라면 NPE(NullPointerException)가 발생
		 * not null 이면 입력값을 그대로 반환함
		 */
		return helloService.sayHello(Objects.requireNonNull(name));
	}
}
