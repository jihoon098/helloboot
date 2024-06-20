package hoonspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
 * 컴포넌트 스캔 -> @ComponentScan
 * : @Component라는 Annotation이 붙은 모든 클래스를 찾아서 빈으로 등록해주는 역할. 이때 DI도 발생한다.
 */
@Configuration
@ComponentScan
public class HellobootApplication {
	
	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}
	
	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}
	
	public static void main(String[] args) {
		
		/*
		 * AnnotationConfigWebApplicationContext
		 * : Annotation이 붙은 Java코드로 만든 구성 정보(Configuration)를 읽을 수 있는 컨테이너
		 */
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();
				
				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
				/*
				 * DispatcherServlet에는 스프링(ApplicationContext)를 주입해 줘야 하는데, 아래 코드를 지워도 동작함.
				 * 그 이유는 DispatcherServlet이 ApplicationContextAware이라는 인터페이스를 구현하고 있기 때문.
				 * 스프링컨테이너는 ApplicationContextAware를 구현하고 있는 클래스에 자기 자신인 애플리케이션 컨텍스트를 주입한다.
				 */
				// dispatcherServlet.setApplicationContext(this);
				
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("Dispatcher Servlet", dispatcherServlet
					
					).addMapping("/*");
				});
				
				webServer.start(); // 웹서버(Tomcat 서블릿 컨테이너) 실행
			}
		};
		
		// Java코드로 된 구성(Config)정보를 가진 클래스를 ApplicationContext에 등록
		applicationContext.register(HellobootApplication.class);
		// 구성된 정보를 이용해 컨테이너를 초기화.
		applicationContext.refresh();
		
	}

}
