package io.osnz

import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RestController
@RequestMapping('/')
@Slf4j
class HelloController {

  @GetMapping
  Mono<String> hello() {
    try {
      TimeUnit.SECONDS.sleep(2)
    } catch (InterruptedException ex) {
      log.error("Unexpected error", ex)
    }
    return Mono.just("Hello World")
  }

  @GetMapping("/{name}")
  Flux<String> sayHello(@PathVariable(name = "name", required = true) String name) {
    try {
      TimeUnit.SECONDS.sleep(1)
    } catch (InterruptedException ex) {
      log.error("Unexpected error", ex)
    }
    return Flux.fromStream(
      IntStream.rangeClosed(1, 10)
        .mapToObj(it -> "Hello ${name}${it} ")
    )
  }

}
