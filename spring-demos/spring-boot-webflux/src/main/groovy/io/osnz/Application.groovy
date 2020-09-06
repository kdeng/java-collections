package io.osnz

import groovy.transform.CompileStatic
import lombok.extern.log4j.Log4j2
import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.config.EnableWebFlux

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@CompileStatic
@EnableWebFlux
@Log4j2
class Application {

  static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application)
    app.bannerMode = Banner.Mode.OFF
    app.run(args)
  }

}
