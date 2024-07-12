package hoonspring.config.autoconfig;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.zaxxer.hikari.HikariDataSource;

import hoonspring.config.ConditionalMyOnClass;
import hoonspring.config.EnableMyConfigurationProperties;
import hoonspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
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
	
}
