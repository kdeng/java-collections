package nz.net.osnz.java;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@EnableAsync
@RestController
public class Application {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @GetMapping(path = "/")
  public String index() {
    return "Hello World";
  }

  @Bean
  public Executor asyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(3);
    executor.setMaxPoolSize(3);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("AsynchThread-");
    executor.initialize();
    return executor;
  }

}

@Service
@Log4j2
class AsyncService {
  @Async("asyncExecutor")
  public CompletableFuture<String> getHelloWorld() throws InterruptedException
  {
    log.info("getHelloWorld starts");
    TimeUnit.SECONDS.sleep(10);
    log.info("getHelloWorld completed");
    return CompletableFuture.completedFuture("Hello world");
  }
}

@RestController
@Log4j2
class AsyncController {

  @Autowired
  private AsyncService asyncService;

  @GetMapping("/async")
  public void asyncGetHelloWorld() throws InterruptedException, ExecutionException {
    log.info("async controller started");
    CompletableFuture<String> getHelloWorld1 = asyncService.getHelloWorld();
    CompletableFuture<String> getHelloWorld2 = asyncService.getHelloWorld();
    CompletableFuture<String> getHelloWorld3 = asyncService.getHelloWorld();

    CompletableFuture.allOf(getHelloWorld1, getHelloWorld2, getHelloWorld3).join();

    log.info("async controller completed");
  }



}
