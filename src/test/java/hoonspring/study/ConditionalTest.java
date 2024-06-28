package hoonspring.study;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class ConditionalTest {
	
	@Test
	void conditional() {
		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		ac.register(Config1.class);
		ac.register(Config2.class);
		ac.refresh();
		
		// true
		MyBean1 bean = ac.getBean(MyBean1.class);
		
		
		// false
		//MyBean2 bean2 = ac.getBean(MyBean2.class);
		
	}
	
	// Assertj에 제공하는 테스트 전용 ApplicationContext. 이 컨테이너를 이용해 Bean의 등록유무를 확인 가능
	@Test
	void conditional2() {
		
		ApplicationContextRunner contextRunner = new ApplicationContextRunner();
		// withUserConfiguration로 적용하고 싶은 class 등록.
		contextRunner.withUserConfiguration(Config1.class)
			.run(context -> {
				/*
				 *  만들어진 context로 테스트 진행 가능.
				 *  hasSingleBean으로 Bean 등록 여부 확인.
				 */
				
				// true
				Assertions.assertThat(context).hasSingleBean(MyBean1.class);
				
				// false
				//Assertions.assertThat(context).hasSingleBean(MyBean2.class);
			});
		
		
		
		new ApplicationContextRunner().withUserConfiguration(Config2.class)
			.run(context -> {
				// 아래 두빈이 다 등록이 안되
				Assertions.assertThat(context).doesNotHaveBean(MyBean2.class);
				Assertions.assertThat(context).doesNotHaveBean(Config2.class);
			});
	}
	
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Conditional(BooleanCondition.class)
	@interface BooleanConditional {
		boolean value();
	}
	
	
	@Configuration
	@BooleanConditional(value = true)
	static class Config1 {
		@Bean
		MyBean1 myBean1() {
			return new MyBean1();
		}	
	}
	
	
	@Configuration
	@BooleanConditional(value = false)
	static class Config2 {
		@Bean
		MyBean2 myBean2() {
			return new MyBean2();
		}
	}
	
	static class MyBean1{}
	
	static class MyBean2{}
	
	
	// Annotation에 붙은 Attribute(Element)의 값들을 활용해서 Condition의 조건을 결정
	static class BooleanCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			
			/* 
			 * AnnotatedTypeMetadata : 파라미터로 넣어준 Condition이 사용되는 @Conditional 어노테이션이 실제로 적용되는 환경에서의 어노테이션의 메타 정보를 다가져음.
			 * 쉽게 말해서 annotation안의 속성 값 읽어오는 것.
			 */
			Map<String, Object> annotationAttribuites = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
			Boolean value = (Boolean)annotationAttribuites.get("value");
			return value;
		}
	}
	
}
