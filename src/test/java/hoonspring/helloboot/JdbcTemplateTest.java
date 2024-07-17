package hoonspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * @JdbcTest
 * : 스프링부트의 테스트용 embedded DB 및 DataSource를 사용할 때 이용하는 어노테이션.
 *   자동구성 중에서 JDBC 이용에 필요한 Bean들만 로딩.
 */
@JdbcTest
public class JdbcTemplateTest {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Test
	void insertAndQuery() {
		jdbcTemplate.update("insert into hello values(?, ?)", "Jihoon", 3);
		jdbcTemplate.update("insert into hello values(?, ?)", "Springboot", 1);
		
		Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
		Assertions.assertThat(count).isEqualTo(2);
	}
	
}
