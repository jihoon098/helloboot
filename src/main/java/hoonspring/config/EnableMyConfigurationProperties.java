package hoonspring.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/*
 * Spring에서 enable로 시작하는 Annotation들의 거의 대부분의 활용 용도는
 * Annotation 내부에 @import Annotation을 다시 넣어서
 * 어떤 기능을 가진 Configuration 또는 ImportSelector와 같은 클래스들을 사용하기위한 것이 목적이다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyConfigurationPropertiesImportSelector.class)
public @interface EnableMyConfigurationProperties {
	Class<?> value();
}
