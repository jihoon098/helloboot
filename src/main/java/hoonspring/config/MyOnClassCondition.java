package hoonspring.config;

import java.util.Map;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

public class MyOnClassCondition implements Condition {
	
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		/*
		 * 어노테이션들의 정보를 이용할수있도록하는 메타데이터로 체크할 클래스의 이름도 가져올 수 있다.
		 * metadata.getAnnotationAttributes()의 파라미터로는 Annotation으로 이름을 텍스트로 전달해야 함.
		 */
		Map<String, Object> attrtibute = metadata.getAnnotationAttributes(ConditionalMyOnClass.class.getName());
		String value = (String) attrtibute.get("value");
		return ClassUtils.isPresent(value, context.getClassLoader());
	}

}
