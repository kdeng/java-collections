package nz.net.osnz.java;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
public class GrpcApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(GrpcApplication.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

}
