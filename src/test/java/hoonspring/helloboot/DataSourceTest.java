package hoonspring.helloboot;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@BootApplicationTest
public class DataSourceTest {
	
	@Autowired // 해당 타입의 빈이 있다면 변수에 주입해주는 어노테이션.
	DataSource dataSource;
	
	
	@Test
	void connect() throws SQLException {
		// hikari 는 커넥션 풀을 만들거나 종료할때 log를 남긴다.
		Connection connection = dataSource.getConnection();
		connection.close();
	}
	
}
