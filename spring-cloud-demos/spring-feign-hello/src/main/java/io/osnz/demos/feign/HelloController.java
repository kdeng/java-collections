package io.osnz.demos.feign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController implements HelloClient {

  public String sayHello(String name) {
    return "Hello " + name;
  }

  public String hello() {
    return "Hello world";
  }

}
