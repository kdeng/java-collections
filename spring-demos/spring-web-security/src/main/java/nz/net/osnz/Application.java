package nz.net.osnz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@RestController
@Slf4j
public class Application {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @GetMapping(path = "/")
  public String index() {
    log.info("request index");
    return "Hello World";
  }

  @GetMapping(path = "/home")
  public String home(@AuthenticationPrincipal User user) {
    log.info("request home");
    return "Welcome to login: " + user.getUsername();
  }

  @GetMapping(path = "/admin")
  public String admin(Principal principal) {
    log.info("request admin");
    Authentication authentication = (Authentication) principal;
    User user = (User) authentication.getPrincipal();
    return "Hey " + user.getUsername();
  }

  @GetMapping(path = "/perform_logout")
  public String performLogout() {
    log.info("request perform logout");
    return "Logging out";
  }

  @GetMapping("/perform_login")
  public String performLogin() {
    log.info("request perform login");
    return "Logging in";
  }

  @GetMapping("/login_error")
  public String loginError() {
    log.info("login error");
    return "Failed to login";
  }

  @GetMapping("/logout_success")
  public String logoutSuccess() {
    log.info("logout success");
    return "Logout success";
  }

}
