package nz.net.osnz.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@RefreshScope
@RestController
public class HelloController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

  @Autowired
  private HelloService service;

  @PostConstruct
  public void postConstruct() {
    LOGGER.info("Instantiated a new HelloController");
  }

  @GetMapping(path = {"/", "/{name}"})
  public String index(@PathVariable(name = "name", required = false) Optional<String> name) {
    return name.isPresent() ? service.sayHello(name.get()) : service.sayHello("World");
  }

}
