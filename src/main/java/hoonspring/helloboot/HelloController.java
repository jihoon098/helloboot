package hoonspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	/*
	 * 아래와 같이 메소드에 파라미터를 지정하면
	 * /hello?name= 식의 query string 호출시 
	 * 메소드가 실행할 때 이름에 해당하는 파라미터에 값 전달해줌.
	 * 
	 * */
	@GetMapping("/hello")
	public String Hello(@RequestParam("name") String name) { 
		return "Hello " + name ;
	}
}
