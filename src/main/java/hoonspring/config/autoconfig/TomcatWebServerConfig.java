package hoonspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import hoonspring.config.ConditionalMyOnClass;
import hoonspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
	
	/*
	 * ConditionalOnMissingBean
	 * : 동일한 타입의 빈이 이미 존재하는가?를 체크하고 존재하지 않으면 빈으로 만듬.
	 *   즉, 사용자가 직접 등록한 빈이 있는지를 확인한 후에, 자동구성 빈을 생성할지 판단
	 */
	@ConditionalOnMissingBean
	@Bean("tomcatWebServerFactory")
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}
	
}
