package net.osnz.module.user.repository;


import io.ebean.EbeanServer;
import net.osnz.module.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unused")
public final class UserRepository {

  private final static Logger log = LoggerFactory.getLogger(UserRepository.class);

  @Autowired
  private EbeanServer ebeanServer;

  public final User get(Long id) {
    return ebeanServer.find(User.class, id);
  }

  public List<User> findAll() {
    return ebeanServer.find(User.class).findList();
  }

  public Long save(User user) {
    Long userId = null;
    try {
      ebeanServer.save(user);
      userId = user.getId();
    } catch (Exception ex) {
      log.error("Failed to save user : " + user.getMobile(), ex);
    }
    return userId;
  }

}
