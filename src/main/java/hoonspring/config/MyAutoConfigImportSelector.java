package hoonspring.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector{
	
	ClassLoader classLoader;
	
	// 생성자를 이용해 classLoader 또한 DI 받을 수 있다.
	MyAutoConfigImportSelector(ClassLoader classLoader){
		this.classLoader = classLoader;
	}
	
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		List<String> autoConfigs = new ArrayList<>();
		
		/*
		 * Import핧 config 클래스 정보를 외부 파일로 분리시킬때 규칙
		 * : META-INF/spring/전체경로를 포함한 annotation 이름.imports} on the classpath.
		 */
		ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(candidate -> autoConfigs.add(candidate));
		
		return autoConfigs.toArray(new String[0]);
	}

}
