package nz.net.osnz.test

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@Configuration
@ComponentScan("nz.net.osnz.test")
@SpringBootApplication
public class Application {

  private static ApplicationContext context;

  public static void main(String[] args) {
//		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
//		context = builder.context();
//		builder.run(args);
    context = SpringApplication.run(Application.class, args);
  }

}
