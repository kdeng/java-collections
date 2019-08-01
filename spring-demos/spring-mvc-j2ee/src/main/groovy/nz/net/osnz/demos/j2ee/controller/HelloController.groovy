package nz.net.osnz.demos.j2ee.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Rest Controller to display "Hello World"
 *
 * @author Kefeng Deng
 */
@RestController
class HelloController {

  @RequestMapping('/')
  public String index() {
    return 'Hello World from Spring Boot!'
  }

  @RequestMapping('/my-filter')
  public String filter() {
    return "Wrong response"
  }

}
