package io.osnz.demo;

import io.osnz.demo.runner.ClassPathCommandRunner;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@Log4j2
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(ClassPathCommandRunner.class, args);
    log.info("Server is ready ...");
  }

}
