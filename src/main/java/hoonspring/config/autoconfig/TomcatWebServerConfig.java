package hoonspring.config.autoconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import hoonspring.config.MyAutoConfiguration;

//imports 대상인 구성 정보 클래스들의 어노테이션은 사용되어지는 어노테이션의 이름으로 맞추는 것이 관례
@MyAutoConfiguration
public class TomcatWebServerConfig {
	
	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}
}
