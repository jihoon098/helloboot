package hoonspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
	
	public static void run(Class<?> applicationClass, String[] args) {
		
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();
				
				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
				
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
