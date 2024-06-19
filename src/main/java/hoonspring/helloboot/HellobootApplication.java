package hoonspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

	public static void main(String[] args) {
		
		/*
		 * 스프링 컨테이너 생성 & Bean 등록
		 * ★ Application Context : 스프링 컨테이너를 대표하는 인터페이스 이름
		 * 
		 * GenericWebApplicationContext
		 * : 기존 applicationContext에서 웹환경에서 쓰이도록 기능이 추가된 것.
		 * 
		 */
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		/* 
		 * 스프링은 클래스의 메타 정보를 등록(register)하는 방식으로 Bean을 생성.
		 * Assembler(= 스프링 컨테이너)는 런타임에 Bean 객체를 생성하며 동시에 의존관계를 맺어줌.
		 */
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(HelloService.class);
		// 구성된 정보를 이용해 컨테이너를 초기화.
		applicationContext.refresh();
		
		
		/* ServletWebServerFactory
		 * : 서블릿 컨테이너의 종류에 종속되지 않도록 추상화한 인터페이스
		 *  
		 * Tomcat ServletWebServerFactory
		 * : Spring에서 Tomcat 서블릿 컨테이너를 코드로 이용할 수 있도록 도와주는 클래스
		 */
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		
		// 웹서버 생성
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			
			/* 
			 * DispatcherServlet
			 * : 프론트 컨트롤러의 많은 기능들(매핑 및 바인딩 등)을 수행하는 Servlet 클래스.
			 * 
			 * 1. Servlet이기 때문에 웹요청이 들어왔을때 해당 정보를 분석해 스프링 컨테이너가 관리하는 Object한테 요청을 넘겨줘야함.
			 * 	  따라서 웹환경에서 사용할 수 있는 application Context가 필요한 것.
			 * 2. DispatcherServlet에게 위 행동을 할 수 있도록 어떻게든 매핑 정보를 줘야하는데,
			 * 	  매핑 정보를 Servlet 내에 코드로 넣는대신 그 요청을 처리할 Controller 클래스에 매핑 정보를 명시하는 방법(Annotation)을 가장 많이 씀.
			 */
			servletContext.addServlet("Dispatcher Servlet", new DispatcherServlet(applicationContext)
			
			).addMapping("/*");
		});
		
		webServer.start(); // 웹서버(Tomcat 서블릿 컨테이너) 실행
	}

}
