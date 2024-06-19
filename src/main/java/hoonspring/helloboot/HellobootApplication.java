package hoonspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

	public static void main(String[] args) {
		
		// 스프링 컨테이너 생성 & Bean 등록
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
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
		
		/* 
		 * 스프링은 클래스의 메타 정보를 등록(register)하는 방식으로 Bean을 생성.
		 * Assembler(= 스프링 컨테이너)는 런타임에 Bean 객체를 생성하며 동시에 의존관계를 맺어줌.
		 */
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(HelloService.class);
		// 구성된 정보를 이용해 컨테이너를 초기화.
		applicationContext.refresh();
		
	}

}
