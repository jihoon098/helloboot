package hoonspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@BootApplicationTest
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
