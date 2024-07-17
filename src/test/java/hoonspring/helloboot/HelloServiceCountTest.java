package hoonspring.helloboot;

import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/*
 * @SpringBootTest
 * : 스프링부트 테스트 환경을 설정하는 어노테이션. 작성한 모든 빈들을 컨테이너로 로딩한다.
 * 
 * WebEnvironment.NONE 옵션
 * : HTTP요청/응답을 처리하는 웹환경 관련 구성 및 웹서버(Tomcat 등)를 실행하지 않고 테스트 수행.
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
public class HelloServiceCountTest {
	
	@Autowired HelloService helloService;
	@Autowired HelloRepository helloRepository;
	@Autowired JdbcTemplate jdbcTemplate;
	
	
	@Test
	void sayHelloIncreaseCount() {
		IntStream.rangeClosed(1, 10).forEach(count -> {
			helloService.sayHello("Toby");
			Assertions.assertThat(helloRepository.countOf("Toby")).isEqualTo(count);
		});
	}
    
}
