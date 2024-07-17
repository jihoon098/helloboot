package hoonspring.helloboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/*
 * @ExtendWith
 * : JUnit5 를 ExtendWith 를 이용하여 SpringExtension.class 확장하면,
 * Spring Context 를 이용하는 SpringContainer 테스트가 가능해짐.
 * 
 * 
 * @ContextConfiguration
 * : 테스트용으로 사용할 빈들의 정보를 Attribute에 넣어주면 됨.
 *   -> 해당 테스트 코드에서는 모든 빈 구성 정보를 끌어오는 시작점인 메인 클래스를 지정.
 * 
 * 
 * @TestPropertySource
 * : 테스트용으로 사용할 프로퍼티 정보를 읽어오도록 classpath를 설정.
 */
/*
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HellobootApplication.class)
@TestPropertySource("classpath:/application.properties")
@Transactional
public @interface BootApplicationTest {
}
*/
