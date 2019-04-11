package nz.net.osnz.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@RestController
public class Application extends SpringBootServletInitializer {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @GetMapping(path = "/")
  public String index(HttpServletRequest request) {
    log.info("Received a request from {}", request.getRemoteHost());
    return "Hello World";
  }

}
