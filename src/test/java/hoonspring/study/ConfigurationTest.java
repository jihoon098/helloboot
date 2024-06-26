package hoonspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
	
	@Test
	void configuration() {
		
		// 테스트 1
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(MyConfig.class);
		ac.refresh();
		
		Bean1 bean1 = ac.getBean(Bean1.class);
		Bean2 bean2 = ac.getBean(Bean2.class);

		Assertions.assertThat(bean1.common).isSameAs(bean2.common);
		
		/* 
		 * 테스트 2
		 * 
		 * MyConfig myConfig = new MyConfig(); 
		 * Bean1 bean1 = myConfig.bean1(); 
		 * Bean2 bean2 = myConfig.bean2();
		 * Assertions.assertThat(bean1.common).isSameAs(bean2.common);
		 */
		
		/*
		 * 테스트 3
		 * 
		 * Common common = new Common();
		 * 
		 * Assertions.assertThat(common).isSameAs(common); // 객체의 주소값까지 같은지 체크
		 * Assertions.assertThat(new Common()).isSameAs(new Common());
		 */
	}
	
	// 테스트 4
	@Test
	void proxyCommonMethod() {
		MyConfigProxy myConfigProxy = new MyConfigProxy();
		
		Bean1 bean1 = myConfigProxy.bean1();
		Bean2 bean2 = myConfigProxy.bean2();
		
		Assertions.assertThat(bean1.common).isSameAs(bean2.common);
	}
	
	
	
	static class MyConfigProxy extends MyConfig {
		private Common common;
		
		@Override
		Common common() {
			if (this.common == null) this.common = super.common();
			
			return this.common;
		}
	}
	
	/*
	 * ※ 중요
	 * 
	 * Configuration의 default값은 proxyBeanMethods = true인데,
	 * 이것은 스프링컨테이너가 시작할때 proxy 만들어서 어떤 팩토리 메소드를 여러번 호출한다 하더라도
	 * 딱 한개의 오브젝트만 생성하고 재사용하게 한다.
	 * 
	 * 
	 * 만약 팩토리 메소드를 통해서 Bean을 만들때, 또 다른 빈 메소드를 호출해서 의존 오브젝트를 가져오는 식의 코드를 작성하지 않았다면
	 * 매번 비용이 드는 프록시를 만드는 방식으로 사용할 필요가 없음. 따라서 이 경우에는 proxyBeanMethods = false 로 해도 된다.
	 * 
	 */
	@Configuration(proxyBeanMethods = false)
	static class MyConfig {
		
		@Bean
		Common common() {
			return new Common();
		}
		
		@Bean
		Bean1 bean1() {
			return new Bean1(common());
		}
		
		@Bean
		Bean2 bean2() {
			return new Bean2(common());
		}
	}
	
	
	static class Bean1 {
		private final Common common;
		
		Bean1(Common common){
			this.common = common;
		}
	}
	
	static class Bean2 {
		private final Common common;
		
		Bean2(Common common){
			this.common = common;
		}
	}
	
	
	static class Common {
	}
	
}
