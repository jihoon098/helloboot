package hoonspring.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import hoonspring.config.autoconfig.DispatcherServletConfig;
import hoonspring.config.autoconfig.TomcatWebServerConfig;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({TomcatWebServerConfig.class, DispatcherServletConfig.class}) // 스캔 대상은 아니지만 클래스(컴포넌트 어노테이션이 붙은) 정보를 넘겨줘서 구성 정보에 추가 가능.
public @interface EnableMyAutoConfiguration {

}
