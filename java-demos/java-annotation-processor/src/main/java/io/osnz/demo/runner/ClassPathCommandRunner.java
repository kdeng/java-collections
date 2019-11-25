package io.osnz.demo.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@Slf4j
@ComponentScan("io.osnz.demo")
public class ClassPathCommandRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    log.info("Start command line runner");
  }

}
