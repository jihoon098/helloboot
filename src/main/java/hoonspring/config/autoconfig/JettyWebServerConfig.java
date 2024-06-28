package hoonspring.config.autoconfig;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

import hoonspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@Conditional(JettyWebServerConfig.JettyCondition.class)
public class JettyWebServerConfig {
	
	@Bean("jettyWebServerFactory")
	public ServletWebServerFactory servletWebServerFactory() {
		return new JettyServletWebServerFactory();
	}
	
	// JettyWebServerConfig 라는 Configuration 클래스를 Bean으로 등록할지 무시할지 boolean Return 정보를 스프링 컨테이너에게 전달
	static class JettyCondition implements Condition {
		
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return true;
		}
	}
	
}
