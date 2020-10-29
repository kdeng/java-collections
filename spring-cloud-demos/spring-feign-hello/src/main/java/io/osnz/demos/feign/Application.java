package io.osnz.demos.feign;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "io.osnz.demos")
@EnableWebMvc
@Log4j2
public class Application {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
    log.info("Server is ready ...");
  }

}
