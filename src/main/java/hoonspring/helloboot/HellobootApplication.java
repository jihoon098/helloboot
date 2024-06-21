package hoonspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/*
 * 컴포넌트 스캔 -> @ComponentScan
 * : @Component라는 Annotation이 붙은 모든 클래스를 찾아서 빈으로 등록해주는 역할. 이때 DI도 발생한다.
 */
@Configuration
@ComponentScan
public class HellobootApplication {
	
	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}
	
	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}
	
	

}
