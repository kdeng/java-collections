package nz.net.osnz.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@RefreshScope
@Service
public class HelloService {

  private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

  @PostConstruct
  public void postConstruct() {
    LOGGER.info("Instantiated a new HelloService");
  }

  public String sayHello(String name) {
    return "Hello " + name;
  }

}
