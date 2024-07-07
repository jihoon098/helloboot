package hoonspring.config.autoconfig;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import hoonspring.config.MyAutoConfiguration;

@MyAutoConfiguration
public class ServerPropertiesConfig {
	
	@Bean
	ServerProperties serverProperties(Environment env) {
		
		/*
		 * Binder
		 * : propertySource의 property 이름과 클래스의 property(field) 이름과 일치하는 것을 찾아서 자동으로 bind 하는 역할.
		 * 
		 * Binder를 이용하면 새로운 property가 추가될 때, 클래스에 변수와 setter만 추가하면 된다.
		 * 따라서 SpringBoot를 볼때 자동구성 빈이 어떤 Properties 클래스를 사용하는가를 확인하는 것이 필요!
		 */
		return Binder.get(env).bind("", ServerProperties.class).get();
	}
	
}
