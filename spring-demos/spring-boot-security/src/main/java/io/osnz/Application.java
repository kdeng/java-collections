package io.osnz;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
@EnableWebMvc
@Log4j2
public class Application {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
    log.info("Server is ready ...");
  }

  @GetMapping(path = "/")
  public String index() {
    return "Hello World";
  }

  @GetMapping(path = "/hello")
  @PreAuthorize("hasAnyRole('ROLE_VIP', 'ROLE_USER')")
  public String hello() {
    return "say hello";
  }

  @GetMapping(path = "/vip")
  @PreAuthorize("hasRole('ROLE_VIP')")
  public String vip() {
    return "say vip";
  }

  @GetMapping(path = "/user")
  @PreAuthorize("hasRole('ROLE_USER')")
  public String user() {
    return "say user";
  }

  @GetMapping(path = "/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String admin() {
    return "say admin";
  }


}
