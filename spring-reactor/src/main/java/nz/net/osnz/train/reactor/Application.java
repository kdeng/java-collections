package nz.net.osnz.train.reactor;

import static reactor.event.selector.Selectors.$;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Bean
    Environment env() {
        return new Environment();
    }
    
    @Bean
    Reactor createReactor(Environment env) {
        return Reactors.reactor()
                .env(env)
                .dispatcher(Environment.THREAD_POOL)
                .get();
    }
    
    @Autowired
    private Reactor reactor;
    
    @Autowired
    private Receiver receiver;
    
    @Autowired
    private Publisher publisher;
    
    @Bean
    Integer numberOfJokes() {
        return 10;
    }
    
    @Bean
    public CountDownLatch latch(Integer numberOfJokes) {
        return new CountDownLatch(numberOfJokes);
    }
    
    @Override
    public void run(String... args) throws Exception {
        reactor.on($("jokes"), receiver);
        publisher.publishJokes();
    }
    
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

}