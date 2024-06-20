package hoonspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
 * @Configuration
 * : 해당 클래스가 스프링의 구성(Config)정보를 가진 클래스라는 것을 스프링에 알려줌.
 * 	 @Configuration이 붙은 클래스는 전체 애플리케이션(즉, Spring Container)를 어떻게 구성할 것인가? 라는 중요한 정보를 가지고 있는 클래스.
 * 	 따라서 애플리케이션 컨텍스트(스프링 컨테이너)에 가장 처음 등록된다.
 */
@Configuration
public class HellobootApplication {
	
	/*
	 * Factory Method 란?
	 * : 단순히 어떤 오브젝트를 생성하는 로직을 담은 Method를 의미
	 * 	 해당 메소드에서 객체 생성 및 의존관계 주입을 다 해주면 됨.
	 * 
	 * @Bean
	 * : Config 클래스내에 해당 어노테이션으로도 스프링에 Bean객체 등록이 가능하다.
	 */
	@Bean
	public HelloController helloControllere(HelloServiceInf helloServiceInf) { //이렇게 파라미터로 넣으면 스프링이 의존 오브젝트를 알아서 넘겨준다.
		return new HelloController(helloServiceInf);
	}
	@Bean
	public HelloServiceInf helloServiceInf() {
		return new HelloService();
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
