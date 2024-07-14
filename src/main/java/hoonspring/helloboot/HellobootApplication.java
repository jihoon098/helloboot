package hoonspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import hoonspring.config.MySpringBootApplication;
import jakarta.annotation.PostConstruct;

@MySpringBootApplication
public class HellobootApplication {
	
	JdbcTemplate jdbcTemplate;
	
	// HellobootApplication 또한 스프링의 빈으로 등록되기 때문에 다른 빈들을 주입받을 수 있음.
	public HellobootApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/*
	 * @PostConstruct
	 * : 개별 빈의 초기화(생성자 호출과 의존성 주입)가 완료된 이후에 실행시킬 메소드에 붙여서 사용.
	 * 
	 * 기존에 SpringFramework에 있는 lifecycle Interface인 InitializingBean을 구현하는 방식을 간결하게 대체하는 용도로 많이 쓰임.
	 * 왜냐면 SpringContainer가 @PostContruct 어노테이션이 붙은 메소드를 찾아서 자동으로 실행시켜주기 때문.
	 * 
	 * ※ ApplicarionRunner 인터페이스를 구현하여 만든 메소드는 전체 어플리케이션의 초기화 작업 이후에 실행됨. 
	 */
	@PostConstruct
	void init() {
		jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}
}
