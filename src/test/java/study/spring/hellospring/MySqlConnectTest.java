package study.spring.hellospring;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** JUnit�� ���ؼ� ����� Ŭ�������� ����� */
// import org.junit.runner.RunWith;
@RunWith(SpringJUnit4ClassRunner.class)

/** �������� �ε��� �� �ֵ��� ��� ���� */
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MySqlConnectTest {
	
	/** DataSource ��ü�� �����ϸ� �����, Spring�� ���ؼ� ���Եȴ�. */
	// import javax.sql.DataSource;
	@Autowired
	DataSource ds;
	
	/** �׽�Ʈ�� �޼��� ���� */
	@Test
	public void testFactory() {
		try {
			/** DB�����ϱ� */
			// import java.sql.Connection;
			Connection con = ds.getConnection();
			System.out.println(con.toString());
			System.out.println("----- DATABASE ���� ���� -----");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
