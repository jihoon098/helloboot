package hoonspring.helloboot;

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
	
	/*
	 * HelloServiceInf 에 어떤 구현체를 넣으라고 명시한 것은 없으나,
	 * 스프링 컨테이너는 현재 컨테이너에 등록된 빈 오브젝트 중에서 주입 후보가 단일로 있다면 자동으로 연결해 줌. Autowiring이라고 부름.
	 */
	public HelloController(HelloServiceInf helloServiceInf) {
		this.helloServiceInf = helloServiceInf;
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam("name") String name) {
		
		if(name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		return helloServiceInf.sayHello(name);
	}
	
	
	@GetMapping("/count")
	public String count(@RequestParam("count") String count) {
		
		return "count" + helloServiceInf.countOf(count);
	}
	
	
}
