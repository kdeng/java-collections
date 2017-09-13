package nz.net.osnz.practice.domain

import com.avaje.ebean.EbeanServer
import nz.ac.auckland.common.testrunner.SimpleTestRunner
import org.junit.BeforeClass

//import com.avaje.ebean.EbeanServer
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SimpleTestRunner.class)
class UserIntegrationTests {


	static EbeanServer server;
	static ApplicationContext ctx;

	@BeforeClass
	public static void init() {
		System.setProperty("dataSource.username", "h2");
		System.setProperty("dataSource.password", "h2");
		System.setProperty("dataSource.url", "jdbc:h2:mem:ebean-domain");
		System.setProperty("dataSource.databaseDriver", "org.h2.Driver");
		System.setProperty("dataSource.ddl.generate", "true");
		System.setProperty("dataSource.ddl.run", "true");
		ctx = new ClassPathXmlApplicationContext("simple-spring.xml");
		server = (EbeanServer) ctx.getBean(EbeanServer.class);
	}

	@Test
	public void testSave() {
		User user = new User();
		user.setName("Bob");
		user.setEmail("aaa@bbb.ccc");

		server.save(user);
		assert server.find(User.class).findList().size() == 1;

	}

}
