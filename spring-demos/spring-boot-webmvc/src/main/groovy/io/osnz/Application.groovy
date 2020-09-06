package io.osnz

import groovy.transform.CompileStatic
import lombok.extern.log4j.Log4j2
import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@CompileStatic
@EnableWebMvc
@Log4j2
class Application {

  static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application)
    app.bannerMode = Banner.Mode.OFF
    app.run(args)
  }

}
