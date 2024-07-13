package hoonspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@BootApplicationTest
public class JdbcTemplateTest {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	/*
	 * BeforeEach : 테스트하기 전에 초기화 작업 진행 가능.
	 */
	@BeforeEach
	void init() {
		// @Transactional 를 사용했기 때문에 DB와의 connect 관련된 것을 신경쓰지 않아도 된다. 따라서 SQL을 이용하는 코드만 작성.
		jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
	}
	
	@Test
	void insertAndQuery() {
		jdbcTemplate.update("insert into hello values(?, ?)", "Jihoon", 3);
		jdbcTemplate.update("insert into hello values(?, ?)", "Springboot", 1);
		
		Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
		Assertions.assertThat(count).isEqualTo(2);
	}
	
}
