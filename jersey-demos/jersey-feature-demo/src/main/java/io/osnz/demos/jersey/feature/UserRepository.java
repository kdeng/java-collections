package io.osnz.demos.jersey.feature;

import org.glassfish.hk2.api.Immediate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {

  private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

  private static final ConcurrentHashMap<Integer, String> NAME_MAP = new ConcurrentHashMap<>();

  public UserRepository() {
    NAME_MAP.put(1, "AAA");
    NAME_MAP.put(2, "BBB");
    NAME_MAP.put(3, "CCC");
    LOG.info("Instantiated UserRepository");
  }

  public String getNameById(Integer id) {
    return NAME_MAP.get(id);
  }

}
