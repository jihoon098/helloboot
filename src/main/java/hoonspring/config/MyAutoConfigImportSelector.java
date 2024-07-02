package hoonspring.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/*
 * Spring은 아래와 같이 DeferredImportSelector를 사용하여 autoConfiguration을 로딩하는데,
 * 이렇게 하는 이유는 사용자 구성정보가 전부 로딩이 되고난 후, 자동 구성정보를 하나씩 하나씩 적용되도록 만들기 위함이다.
 * 
 * 즉, @Conditional을 판단하는 시점은 ComponentScan대상인 사용자 구성정보는 이미 빈으로 등록된 이후라는 것!!
 */
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
