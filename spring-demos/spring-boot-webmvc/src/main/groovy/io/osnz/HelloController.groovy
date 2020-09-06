package io.osnz

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RestController
@RequestMapping('/')
@CompileStatic
class HelloController {

  @GetMapping
  public String hello() {
    return 'Hello World'
  }

  @GetMapping('/{name}')
  public String sayHello(@PathVariable(name = 'name', required = true) String name) {
    return "Hello ${name}"
  }

}
