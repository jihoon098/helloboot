package hoonspring.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * @ComponentScan은 basePackages옵션을 따로 설정하지 않으면,
 * 기본적으로 해당 어노테이션이 선언된 패키지를 기준으로 클래스들을 스캔함.
 * 즉, 어떤 클래스를 다른 패키지로 옮기면 스캔 대상에서 제외됨.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration
@ComponentScan
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {

}
