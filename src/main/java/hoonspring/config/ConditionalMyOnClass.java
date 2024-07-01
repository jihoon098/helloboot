package hoonspring.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(MyOnClassCondition.class)
/*
 * 관례적으로 @Conditional을 Meta-Annotation으로 가지는 Annotation의 이름은 Conditional로 시작.
 * Custom 어노테이션을 만드는 이유는 Condition에서 읽어갈 attribute(element) 값을 주기 위해서이다.
 */
public @interface ConditionalMyOnClass {
	
	// 클래스가 존재하는지를 체크할 수 있는 클래스의 이름을 전달하기위해 선언.. MyOnClassCondition.class에서 해당 값을 사용
	String value();
}
