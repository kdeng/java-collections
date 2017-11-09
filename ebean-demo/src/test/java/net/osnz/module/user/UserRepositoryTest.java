package net.osnz.module.user;

import net.osnz.module.user.domain.User;
import net.osnz.module.user.repository.UserRepository;
import nz.ac.auckland.agent.AgentLoader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app-test-config.xml"})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {

	protected final static Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

	@Inject
	private UserRepository userRepository;

	@BeforeClass
	public static void initAgent() {
		AgentLoader.findAgentInClasspath("avaje-ebeanorm-agent","debug=1");
//		AgentLoader.loadAgent(System.getProperty("user.home") + "/.m2/repository/org/avaje/ebeanorm/avaje-ebeanorm-agent/4.5.3/avaje-ebeanorm-agent-4.5.3.jar");
	}

	@Before
	public void init() {
		log.info("Before TEST");
	}

	@Test
	public void testFindAll() {
		log.debug("starting testFindAll");
		List<User> users = userRepository.findAll();
		log.debug("Current there are " + users.size() + " users.");
		for (User user : users) {
			log.debug(" User :" + user);
		}
		assert users != null;
		assert users.size() == 4;
	}

	@Test
	public void testGet() {
		log.debug("staring testGet");
		User user = userRepository.get(1L);
		assert user != null;
		assert user.getMobile().equals("021856068");
	}

	@Test
	public void testSave() {
		log.debug("starting testSave");

		User user = new User();
		user.setCreditPoint(111);
		user.setDateVerifiedEmail(new Date());
		user.setEmail("k.deng@51any.com");
		user.setMobile("021111222");
		user.setPassword("12323");

		user.setPendingExpense(new BigDecimal(0));
		user.setTotalExpense(new BigDecimal("12.33"));

		assert 5 == userRepository.save(user);

	}

}
