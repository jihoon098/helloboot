package hoonspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {
	
	// 서버를 띄운 이후에 Api 요청 Test 진행
	@Test
	void HelloApi() {
		/*
		 * http localhost:8080/hello?name=Spring
		 * 
		 * RestTemplate : 위 HTTP형식의 Api요청을 코드로 해주도록 스프링이 만들어준 클래스
		 * -> 해당 클래스를 이용하면 API요청 및 응답을 받을 수 있음. 정상적인 응답의 경우는 문제없지만 400번때나 500번때 에러가 발생하면 예외를 던짐.
		 * 물론 예외처리를 하면되지만. 그냥 응답 자체를 전부 가져와서 상태코드, 컨텐츠 등을 파악하려면 TestRestTemplate클래스가 훨씬 편리함.
		 */
		TestRestTemplate rest = new TestRestTemplate();
		// ResponseEntity : 웹응답의 모든 요소를 다 가지고 있는 오브젝트. 그리고 <String>은 body가 String이라는 의미.
		ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

		// Assertion 검증 라이브러리 사용
		// 웹응답 3가지 요소 검증
		Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
		// 2. header(content-type) text/plain
		Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
		// 3. body 내용
		Assertions.assertThat(res.getBody()).isEqualTo("Hello Spring");
	}
}
