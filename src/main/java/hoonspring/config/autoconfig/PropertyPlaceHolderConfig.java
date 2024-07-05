package hoonspring.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import hoonspring.config.MyAutoConfiguration;

@MyAutoConfiguration
public class PropertyPlaceHolderConfig {
	
	/* 
	 * PropertySourcesPlaceholderConfigurer 란?
	 * 
	 * Environment 추상화로 설정된 각종 Property Sources 들로부터 Placeholder에 Property값을 가져와 교체해주는 기능을 수행.
	 * BeanFactoryPostProcessor 를 구현하고 있는 이 클래스는 Bean을 정의하는 기본적인 정보를 모은 다음에 후처리를 실행하는 Spring에서 제공하는 Configurer.
	 */
	@Bean
	PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
