package io.osnz;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@RestController
@EnableWebMvc
@Log4j2
public class Application {

  @Value("${my.name:myName}")
  String myName = "myName";

  @Value("${my.name1}")
  String myName1 = "myName1";

  @Value("${my.name2}")
  String myName2 = "myName2";

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
    log.info("Server is ready ...");
  }

  @GetMapping(path = "/")
  public String index() {
    return myName + " : " + myName1 + " : " + myName2;
  }

}
