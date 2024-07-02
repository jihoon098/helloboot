package hoonspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import hoonspring.config.ConditionalMyOnClass;
import hoonspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.eclipse.jetty.util.Jetty")
public class JettyWebServerConfig {
	
	@ConditionalOnMissingBean
	@Bean("jettyWebServerFactory")
	public ServletWebServerFactory servletWebServerFactory() {
		return new JettyServletWebServerFactory();
	}
	
}
