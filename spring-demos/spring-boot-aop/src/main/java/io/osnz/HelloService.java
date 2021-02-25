package io.osnz;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Audit("Instantiated")
@Service
public class HelloService {
  private final LogService logService;

  public HelloService(LogService logService) {
    this.logService = logService;
  }

  @PostConstruct
  public void postConstruct() {
    logService.info("Post constructor for helloService");
  }

  @Audit(message = "Invoke hello with {}", level = Audit.Level.INFO)
  public String hello(String name) {
    return "Hello " + name;
  }

}
