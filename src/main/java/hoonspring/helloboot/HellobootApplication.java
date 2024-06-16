package hoonspring.helloboot;

import java.io.IOException;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
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
			// 생성한 서블릿 컨테이너에 서블릿을 추가
			servletContext.addServlet("hello", new HttpServlet() {
				/*
				 * 서블릿내에 웹요청 및 응답에 대한 Object가 파라미터로 전달되는 것을 볼 수 있음.
				 */
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 요청 Object 처리
					String name = req.getParameter("name");
					
					// 응답 Object 처리
					resp.setStatus(HttpStatus.OK.value());
					resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
					resp.getWriter().println("Hello " + name);
				}
			}).addMapping("/hello"); // URL 매핑
		});
		
		webServer.start(); // 웹서버(Tomcat 서블릿 컨테이너) 실행
	}

}
