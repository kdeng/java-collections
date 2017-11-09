package nz.net.osnz.demos.reactor;

//import static reactor.event.selector.Selectors.$;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import reactor.Environment;
import reactor.bus.EventBus;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static reactor.bus.selector.Selectors.$;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	@Bean
	Environment env() {
		return new Environment();
	}

	@Bean
	EventBus createReactor(Environment env) {
		return EventBus.create(env, Environment.THREAD_POOL);
	}

	@Autowired
	private EventBus eventBus;

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

	public void run(String... args) throws Exception {
		logger.info("Start to run command line runner");
		eventBus.on($("jokes"), receiver);
		publisher.publishJokes(10);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(Application.class);
		app.setBannerMode(Banner.Mode.OFF);
		ApplicationContext applicationContext = app.run(args);
		applicationContext.getBean(CountDownLatch.class).await(1, TimeUnit.SECONDS);
		applicationContext.getBean(Environment.class).shutdown();
	}


	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
		factory.setPort(9000);
		factory.setSessionTimeout(10, TimeUnit.MINUTES);
		return factory;
	}

}