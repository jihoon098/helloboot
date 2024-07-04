package hoonspring.helloboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import hoonspring.config.MySpringBootApplication;

@MySpringBootApplication
public class HellobootApplication {
	
	@Bean
	ApplicationRunner applicationRunner(Environment env) { // 컨테이너로부터 Environment 객체를 주입받음
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				String priority = env.getProperty("my.priority");
				System.out.println("Environment 의 현재 우선순위는 " + priority);
			}
		};
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}
}
