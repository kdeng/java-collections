package io.osnz.demos.jersey.feature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class UserService {

  private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

  private final UserRepository userRepository;

  @Inject
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
    LOG.info("Instantiated UserService");
  }

  public String findNameById(String id) {
    return userRepository.getNameById(Integer.parseInt(id));
  }

}
