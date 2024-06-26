package hoonspring.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import hoonspring.config.MyAutoConfiguration;

//imports 대상인 구성 정보 클래스들의 어노테이션은 사용되어지는 어노테이션의 이름으로 맞추는 것이 관례
@MyAutoConfiguration
public class DispatcherServletConfig {
	
	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}
}
