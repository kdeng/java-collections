package nz.net.osnz.demos.jersey;


import io.netty.channel.Channel;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.net.URI;

public class App {

  private static final Logger LOG = LoggerFactory.getLogger(App.class);

  public static final String ROOT_PATH = "hello";

  public static final URI BASE_URI = URI.create("http://localhost:8080/");

  public static void main(String[] args) {
    try {
      LOG.info("Starting jersey on netty container");

      final ResourceConfig resourceConfig = new ResourceConfig(
          HelloApi.class,
          UserApi.class
      ).register(new AbstractBinder() {
        @Override
        protected void configure() {
          bind(HelloService.class).to(HelloService.class).in(Singleton.class);
//          bind(JacksonFeature.class).to(JacksonFeature.class).in(Singleton.class);
          bind(UserMessageBodyWriter.class).in(Singleton.class);
        }
      });

      final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, resourceConfig, null);
      Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close()));
      LOG.info("Application started. (HTTP/2 enabled!)\nTry out {}{}\nStop the application using CTRL+C.", BASE_URI, ROOT_PATH);

      Thread.currentThread().join();

    } catch (InterruptedException ex) {
      LOG.error("Service is down", ex);
    }

  }

}
