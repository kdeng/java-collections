package net.osnz.module.user;

import net.osnz.module.user.domain.User;
import net.osnz.module.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {TestConfig.class})
public class UserRepositoryTest {

    protected final static Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void shouldInstantiatedUserRepositoryProperly() {
        Assert.assertNotNull(userRepository);
    }


    @Test
    public void testFindAll() {
        List<User> users = userRepository.findAll();
        Assert.assertNotNull(users);
        Assert.assertEquals(4, users.size());
    }

    @Test
    public void testGet() {
        log.debug("staring testGet");
        User user = userRepository.get(1L);
        assert user != null;
        assert user.getMobile().equals("012345678");
    }

    @Test
    public void testSave() {
        log.debug("starting testSave");

        User user = new User();
        user.setCreditPoint(111);
        user.setDateVerifiedEmail(new Date());
        user.setEmail("eee@eee.com");
        user.setMobile("87654321");
        user.setPassword("12323");
        user.setPendingExpense(new BigDecimal(0));
        user.setTotalExpense(new BigDecimal("12.33"));
        userRepository.save(user);

        List<User> allUesrs = userRepository.findAll();


        Assert.assertEquals(5, allUesrs.size());

    }

}
