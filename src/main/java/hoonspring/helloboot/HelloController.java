package hoonspring.helloboot;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * dispatcherServle은 먼저 클래스 레벨의 정보를 먼저 참고한 후, 메소드 레벨의 정보를 추가함.
 * (hello URI를 클래스 레벨이 아닌 메소드들에게만 명시해도 당연히 됨)
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
	// final은 정의할 때부터 초기화 or 생성자에서 초기화 코드가 필요
	private final HelloServiceInf helloServiceInf;
	
	public HelloController(HelloServiceInf helloServiceInf) {
		this.helloServiceInf = helloServiceInf;
	}
	
	/*
	 * ★ dispatcherServlet의 기본 동작은 String이 return되면 View 이름으로 해석한다!!
	 * 현재는 문자열의 return을 기대하기에 @ResponseBody 사용.
	 */
	@GetMapping
	@ResponseBody
	public String hello(@RequestParam("name") String name) {
		
		/*
		 * Objects.requireNonNull <- Null 체크를 위한 메소드
		 * 파라미터로 입력된 값이 null 이라면 NPE(NullPointerException)가 발생
		 * not null 이면 입력값을 그대로 반환함
		 */
		return helloServiceInf.sayHello(Objects.requireNonNull(name));
	}
}
