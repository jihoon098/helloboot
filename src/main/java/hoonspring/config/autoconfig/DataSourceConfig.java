package hoonspring.config.autoconfig;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import hoonspring.config.ConditionalMyOnClass;
import hoonspring.config.EnableMyConfigurationProperties;
import hoonspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
/*
 * EnableTransactionManagement 
 * : 해당 어노테이션은 AOP를 활용하여 여러 구성 정보를 읽어와 @Transactional 을 사용할 수 있게 한다.
 * 
 * ※ Enable이란 단어가 들어간 어노테이션 내부에선 구성정보를 가진 클래스를 Import한다는 사실을 항상 인지하자!!
 */
@EnableTransactionManagement
public class DataSourceConfig {
	
	// Hikari DataSource 팩토리 메소드
	@Bean
	@ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
	@ConditionalOnMissingBean
	DataSource hikariDataSource(MyDataSourceProperties properties) {
		
		// Connection Pool을 지원하는 대표적인 DataSource
		HikariDataSource dataSource = new HikariDataSource();
		
		dataSource.setDriverClassName(properties.getDriverClassName());
		dataSource.setJdbcUrl(properties.getUrl());
		dataSource.setUsername(properties.getUsername());
		dataSource.setPassword(properties.getPassword());
		
		return dataSource;
	}
	
	
	// SimpleDriver DataSource 팩토리 메소드
	@Bean
	@ConditionalOnMissingBean
	DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
		
		/*
		 * SimpleDriverDataSource
		 * : spring에서 제공하는 단순한 DataSource 구현 클래스. Pool이 없어서 매번 새로운 Connection을 만듬.
		 */
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		// Class.forName : 이름으로 Class타입 객체를 반환
		dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
		dataSource.setUrl(properties.getUrl());
		dataSource.setUsername(properties.getUsername());
		dataSource.setPassword(properties.getPassword());
		
		return dataSource;
	}
	
	
	// Jdbc Template
	/*
	 * @ConditionalOnSingleCandidate(Class<?>)
	 * : 메소드가 실행될 때, 스프링 컨테이너의 빈 구성 정보에 파라미터 타입의 빈이 딱 한 개만 등록이 되어 있다면 그 빈을 가져와서 사용하겠다.
	 */
	@Bean
	@ConditionalOnSingleCandidate(DataSource.class)
	@ConditionalOnMissingBean
	JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	
	// Jdbc TransactionManager
	@Bean
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    JdbcTransactionManager jdbcTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }
}
