package hoonspring.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import hoonspring.config.ConditionalMyOnClass;
import hoonspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
	
	@Value("${contextPath}") // PropertySourcesPlaceholderConfigurer를 통해 contextPath라는 이름의 Property값을 주입받음
	String contextPath;
	
	@ConditionalOnMissingBean
	@Bean("tomcatWebServerFactory")
	public ServletWebServerFactory servletWebServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.setContextPath(this.contextPath);
		return factory;
	}
	
}
