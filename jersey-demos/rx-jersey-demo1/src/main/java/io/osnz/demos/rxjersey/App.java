package io.osnz.demos.rxjersey;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import io.netty.channel.Channel;
import io.osnz.demos.rxjersey.api.ApiResource;
import io.osnz.demos.rxjersey.api.UserResource;
import io.osnz.demos.rxjersey.provider.JacksonContextProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * @author Kefeng Deng (kefeng.deng@clearpoint.co.nz)
 */
public class App {

  private static final Logger LOG = LoggerFactory.getLogger(App.class);

  public static final URI BASE_URI = URI.create("http://localhost:9090/");

  public static void main(String[] args) {
    try {
      LOG.info("Starting jersey on netty container");

      final ResourceConfig resourceConfig = new ResourceConfig();

      resourceConfig.register(ApiResource.class);
      resourceConfig.register(UserResource.class);
      resourceConfig.register(JacksonContextProvider.class);

      final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, resourceConfig, null);
      Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close()));
      LOG.info("Application started. (HTTP/2 enabled!)\nTry out {}\nStop the application using CTRL+C.", BASE_URI);

      Thread.currentThread().join();

    } catch (InterruptedException ex) {
      LOG.error("Service is down", ex);
    }

  }

}
