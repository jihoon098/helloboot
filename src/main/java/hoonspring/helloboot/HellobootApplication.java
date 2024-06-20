package hoonspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
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
	
	public static void main(String[] args) {
		
		/*
		 * AnnotationConfigWebApplicationContext
		 * : Annotation이 붙은 Java코드로 만든 구성 정보(Configuration)를 읽을 수 있는 컨테이너
		 */
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();
				
				/* 
				 * 스프링 컨테이너 초기화를 진행하면서 서블릿 컨테이너도 초기화
				 *  -> 서블릿 컨테이너 생성 & 서블릿(Dispatcher Servlet) 등록
				 */
				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("Dispatcher Servlet", new DispatcherServlet(this)
					
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
