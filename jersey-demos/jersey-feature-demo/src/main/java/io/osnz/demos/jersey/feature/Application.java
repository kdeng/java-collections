package io.osnz.demos.jersey.feature;

import io.netty.channel.Channel;
import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.net.URI;

/**
 * @author Kefeng Deng
 * @since 2019-05-22
 */
public class Application {

  private static final Logger LOG = LoggerFactory.getLogger(Application.class);

  public static final URI BASE_URI = URI.create("http://localhost:9000/");

  public static void main(String[] args) {
    try {
      LOG.info("Starting jersey on netty container");

      final ResourceConfig resourceConfig = new ResourceConfig();

      resourceConfig.register(UserFeature.class);
//      resourceConfig.register(new UserBinder());

      final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, resourceConfig, null);
      Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close()));
      LOG.info("Application started. (HTTP/2 enabled!)\nTry out {}\nStop the application using CTRL+C.", BASE_URI);

      Thread.currentThread().join();

    } catch (InterruptedException ex) {
      LOG.error("Service is down", ex);
    }

  }

}
