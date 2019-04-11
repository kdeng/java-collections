package net.osnz.module.user;

import net.osnz.module.user.domain.User;
import net.osnz.module.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {TestConfig.class})
public class UserServiceTest {

  @Autowired
  private UserRepository userRepository;


  @Test
  public void shouldInsertNewUserProperly() {
    List<User> users = userRepository.findAll();
    Assertions.assertThat(users.size()).isEqualTo(4);

    User newUser = new User();
    newUser.setMobile("021999888777");
    newUser.setPassword("1234567");
    userRepository.save(newUser);

    users = userRepository.findAll();
    Assertions.assertThat(users.stream().anyMatch(u -> u.getMobile().equals("021999888777"))).isTrue();
  }

}
