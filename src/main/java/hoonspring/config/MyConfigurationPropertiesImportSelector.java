package hoonspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

/*
 * ImportSelector의 기능은 실제로 import할 클래스의 이름을 Return하는 역할이다.
 */
public class MyConfigurationPropertiesImportSelector implements DeferredImportSelector {
	
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		
		MultiValueMap<String, Object> attrs = importingClassMetadata.getAllAnnotationAttributes(EnableMyConfigurationProperties.class.getName());
		Class propertyClass = (Class) attrs.getFirst("value"); // get()은 List로 return. 간단하게 하나의 값만 가져오기 위해 getFirst() 사용.
		
		return new String[] { propertyClass.getName() };
	}
	
}
