package hoonspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class HellobootApplication {

	public static void main(String[] args) {
		/* ServletWebServerFactory
		 * : 서블릿 컨테이너의 종류에 종속되지 않도록 추상화한 인터페이스
		 *  
		 * Tomcat ServletWebServerFactory
		 * : Spring에서 Tomcat 서블릿 컨테이너를 코드로 이용할 수 있도록 도와주는 클래스
		 */
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		
		WebServer webServer = serverFactory.getWebServer(); // 웹서버 생성
		webServer.start(); // 웹서버(Tomcat 서블릿 컨테이너) 실행
	}

}
