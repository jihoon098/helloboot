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
	
	@ConditionalOnMissingBean
	@Bean("tomcatWebServerFactory")
	public ServletWebServerFactory servletWebServerFactory(ServerProperties serverProperties) {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		
		factory.setContextPath(serverProperties.getContextPath());
		factory.setPort(serverProperties.getPort());		
		return factory;
	}
	
}
