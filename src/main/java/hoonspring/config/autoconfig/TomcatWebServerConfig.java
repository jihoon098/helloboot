package hoonspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import hoonspring.config.ConditionalMyOnClass;
import hoonspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
	
	@ConditionalOnMissingBean
	@Bean("tomcatWebServerFactory")
	public ServletWebServerFactory servletWebServerFactory(Environment env) {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.setContextPath(env.getProperty("contextPath"));
		return factory;
	}
	
}
