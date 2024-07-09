package hoonspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import hoonspring.config.ConditionalMyOnClass;
import hoonspring.config.EnableMyConfigurationProperties;
import hoonspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
/*
 * @Import를 사용했을 때는 어떤 목적으로 import를 한거지?? 라는 생각을 갖게 만들 수 있다.
 * 이 때, Enable이라는 이름의 Annotation을 이용해서 정의함으로써 더 명시적으로 표현하는 것이 springboot의 스타일.
 */
@EnableMyConfigurationProperties(ServerProperties.class)
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