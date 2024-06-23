package hoonspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

	/* 
	 * @Test를 활용하면 장점
	 * 1. 서버를 띄우지않고도 간결하게 하나의 대상(클래스)을 단위 테스트 할 수 있음.
	 * 2. 다른 의존 오브젝트를 신경쓰지 않아도 됨. (주입은 해줘야 함)
	 */
	@Test
	void helloController() {
		HelloController helloController = new HelloController(name -> name);
		
		String ret = helloController.hello("Test");
		
		Assertions.assertThat(ret).isEqualTo("Test");
	}
	
	@Test
	void failsHelloController() {
		HelloController helloController = new HelloController(name -> name);
		
		// assertThatThrownBy() 파라미터안에 예의가 던져지는 코드를 Lambda식으로 넣어주면 됨
		Assertions.assertThatThrownBy(() -> {
			helloController.hello(null);
		}).isInstanceOf(IllegalArgumentException.class);
		
		Assertions.assertThatThrownBy(() -> {
			helloController.hello(" ");
		}).isInstanceOf(IllegalArgumentException.class);
	}
}
