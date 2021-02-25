package io.osnz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RestController
public class HelloController {
  private final HelloService helloService;

  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }

  @GetMapping(path = "/{name}")
  public String index(@PathVariable("name") String name) {
    return helloService.hello(name);
  }

}
