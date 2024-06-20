package hoonspring.helloboot;

import java.util.Objects;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * 스프링부트3에서 스프링 버전이 업데이트되면서
 * @RequestMapping만으로 DispatcherServlet이 인식하던 기능이 더 이상 지원되지 않고
 * @Controller까지 등록이 필요하게 바뀌게됨. (Component도 안됨. Controller로 써야함)
 * 그래서 @RequestMapping만 있을 때 스프링 컨트롤러로 등록이 안 되어서 에러 발생.
 * 
 * @RestController는 @ResponseBody를 메타 어노테이션으로 가지고 있음. DispatcherServlet가 이것을 인식.
 */
@RestController
public class HelloController {
	// final은 정의할 때부터 초기화 or 생성자에서 초기화 코드가 필요
	private final HelloServiceInf helloServiceInf;
	private final ApplicationContext applicationContext;
	
	/*
	 * applicationContext 타입의 오브젝트 또한 SpringContainer입장에서는 자신이 관리하는 Bean 오브젝트.
	 * 그래서 아래와 같이 생성자의 파라미터를 통해 DI 받을 수 있는 것.
	 */
	public HelloController(HelloServiceInf helloServiceInf, ApplicationContext applicationContext) {
		this.helloServiceInf = helloServiceInf;
		this.applicationContext = applicationContext;
		
		System.out.println("★ Bean객체에서 ApplicationContext 오브젝트 주입받기 : " + applicationContext);
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam("name") String name) {
		
		/*
		 * Objects.requireNonNull <- Null 체크를 위한 메소드
		 * 파라미터로 입력된 값이 null 이라면 NPE(NullPointerException)가 발생
		 * not null 이면 입력값을 그대로 반환함
		 */
		return helloServiceInf.sayHello(Objects.requireNonNull(name));
	}
}
