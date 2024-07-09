package hoonspring.config.autoconfig;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

import hoonspring.config.MyAutoConfiguration;
import hoonspring.config.MyConfigurationProperties;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {
	
	/*
	 * BeanPostProcessor
	 * 
	 * : Spring에 모든 Bean이 생성(의존 주입까지 완료)된 다음에, 그렇게 만들어진 Bean 오브젝트를 가공할 수 있는 후처리 인터페이스.
	 */
	@Bean
	static BeanPostProcessor propertyPostProcessor(Environment environment) {
	/*
	 * static을 붙인 팩토리 메소드들은 @Configuration 클래스의 객체가 생성되기 전에 초기화가 진행된다.
	 * 즉, 다른 클래스들보다 먼저 Bean등록이 되기 때문에 스프링 컨테이너의 라이프사이클 문제를 회피할 수 있다.
	 */
		return new BeanPostProcessor() {
			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				
				/*
				 * AnnotationUtils
				 * 
				 * : 클래스에 특정 어노테이션이 붙어있는지 확인할 수 있는 Spring Framework 의 Utility.
				 * 어노테이션이 발견되면 해당 어노테이션의 인스턴스를 반환, 그렇지 않으면 null 반환.
				 */
				MyConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), MyConfigurationProperties.class);
				if (annotation == null) return bean;
				
				// Annotation의 모든 Elements(Attributes)를 Key, value 형태인 Map으로 get
				Map<String, Object> attrs = AnnotationUtils.getAnnotationAttributes(annotation);
				String prefix = (String) attrs.get("prefix");
				
				// 바인딩할때 propertySource에서 prefix를 확인하고 뒤의 이름이 클래스의 멤버변수명과 일치하는가 체크.
				return Binder.get(environment).bindOrCreate(prefix, bean.getClass());
			}
		};
	}

}
