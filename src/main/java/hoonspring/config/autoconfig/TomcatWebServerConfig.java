package hoonspring.config.autoconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import hoonspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@Conditional(TomcatWebServerConfig.tomcatCondition.class)
public class TomcatWebServerConfig {
	
	@Bean("tomcatWebServerFactory")
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}
	
	
	static class tomcatCondition implements Condition {
		
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			/*
			 * ClassUtils
			 * : 특정 라이브러리(클래스 파일)가 프로젝트에 포함되어 있는가를 체크할 수 있는 스프링에서 제공하는 유틸리티
			 *   클래스 이름은 Full Path를 적어야하기 때문에 패키지 이름까지 전부 써야 함.
			 */
			return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.getClassLoader());
		}
	}
	
}
