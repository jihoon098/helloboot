package hoonspring.helloboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@BootApplicationTest
public class HelloRepositoryTest {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	HelloRepository helloRepository;
	
	@BeforeEach
	void init() {
		jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
	}
	
	@Test
	void findHelloFailed() {
		assertThat(helloRepository.findHello("Jihoon")).isNull();
	}
	
	@Test
	void incraseCount() {
		assertThat(helloRepository.countOf("Jihoon")).isEqualTo(0);
		
		helloRepository.increaseCount("Jihoon");
		assertThat(helloRepository.countOf("Jihoon")).isEqualTo(1);
		
		helloRepository.increaseCount("Jihoon");
		assertThat(helloRepository.countOf("Jihoon")).isEqualTo(2);
	}
}
