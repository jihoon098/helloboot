package hoonspring.helloboot;

import java.io.IOException;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HellobootApplication {

	public static void main(String[] args) {
		/* ServletWebServerFactory
		 * : 서블릿 컨테이너의 종류에 종속되지 않도록 추상화한 인터페이스
		 *  
		 * Tomcat ServletWebServerFactory
		 * : Spring에서 Tomcat 서블릿 컨테이너를 코드로 이용할 수 있도록 도와주는 클래스
		 */
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		
		// 웹서버 생성
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			
			HelloController helloController = new HelloController();
			
			// 생성한 서블릿 컨테이너에 서블릿을 추가
			servletContext.addServlet("Front Controller", new HttpServlet() {
				/*
				 * 서블릿내에 웹요청 및 응답에 대한 Object가 파라미터로 전달되는 것을 볼 수 있음.
				 */
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					
					/* 
					 * 서블릿 컨테이너의 매핑 기능을 프론트 컨트롤러가 담당.
					 * 매핑 : 요청 정보(HTTP Method, Request URI 등등)를 통해 진행됨.
					 */
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						// 요청 Object 처리
						String name = req.getParameter("name");
						
						/*
						 * Front Controller는 직접적으로 웹요청과 엑세스하고
						 * 웹기술과 관련된 Object들(상태값, 요청, Path 정보 등등)에게서
						 * 평범한 데이터타입으로 전환하여 비지니스를 처리하는 Object에게 파라미터를 바인딩해줌.
						 * 기존 컨트롤러는 직접적인 웹요청과 분리됨. 
						 */
						String ret = helloController.hello(name);
						
						// 응답 Object 처리
						resp.setStatus(HttpStatus.OK.value());
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					}
					else if (req.getRequestURI().equals("/user")) {
						System.out.println("user URI로 매핑");
					}
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value()); //404
					}
					
				}
			}).addMapping("/*"); // Front Controller 라는 개념은 중앙화된 처리를 위해 모든 요청을 다 받을 수 있도록 하는 것.
		});
		
		webServer.start(); // 웹서버(Tomcat 서블릿 컨테이너) 실행
	}

}
