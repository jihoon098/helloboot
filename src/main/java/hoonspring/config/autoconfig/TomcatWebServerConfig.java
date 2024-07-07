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
	
	@Value("${contextPath:}") // PropertySourcesPlaceholderConfigurer를 통해 contextPath라는 이름의 Property값을 주입받음
	String contextPath;
	
	/* 
	 * property는 기본적으로 text로 된 파일의 내용에 작성하지만,
	 * Spring이 변환가능한 Integer등의 타입이면 해당 타입의 값으로 주입해줌.
	 */
	@Value("${port:9090}") // default값은 콜론(:)뒤에 지정 가능하다. Placeholder에 치환할 이름의 값이 없다면 default값이 사용된다.
	int port;
	
	@ConditionalOnMissingBean
	@Bean("tomcatWebServerFactory")
	public ServletWebServerFactory servletWebServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		
		factory.setContextPath(this.contextPath);
		factory.setPort(this.port);		
		return factory;
	}
	
}
