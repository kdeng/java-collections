package nz.net.osnz.demos.rxjava

import groovy.transform.CompileStatic
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@CompileStatic
@EnableScheduling
public class Application implements CommandLineRunner {

  private static final Logger LOG = LoggerFactory.getLogger(Application)

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application)
    app.bannerMode = Banner.Mode.OFF
    app.run(args)
    LOG.info("Startup application")
  }

  @Override
  void run(String... args) throws Exception {
    LOG.info("Command Line Run...")
    mapActivity()
  }

  void mapActivity() {
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> e) throws Exception {
        e.onNext(1);
        e.onNext(2);
        e.onNext(3);
      }
    }).map(new Function<Integer, String>() {
      @Override
      public String apply(Integer integer) throws Exception {
        return "This is result " + integer;
      }
    }).subscribe(new Consumer<String>() {
      @Override
      public void accept(String s) throws Exception {
        LOG.info("{} accept: {}", Thread.currentThread().getName(), s)
      }
    });
  }

}
