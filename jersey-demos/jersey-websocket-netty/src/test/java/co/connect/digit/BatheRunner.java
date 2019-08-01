package co.connect.digit;

import bathe.BatheBooter;
import cd.connect.jersey.common.CommonConfiguration;
import cd.connect.jersey.common.JerseyExceptionMapper;
import cd.connect.lifecycle.ApplicationLifecycleManager;
import cd.connect.lifecycle.LifecycleStatus;
import co.connect.digit.api.HelloResource;
import io.netty.channel.Channel;
import nz.co.asb.engagement.filter.CORSResponseFilter;
import co.connect.digit.processor.DelayInboundProcessor;
import co.connect.digit.processor.HelloInboundProcessor;
import nz.co.asb.engagement.websocket.DualNettyServerProvider;
import nz.co.asb.engagement.websocket.processor.WebSocketInboundProcessor;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.logging.JerseyServerLogger;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class BatheRunner {

  public static class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
      ApplicationLifecycleManager.updateStatus(LifecycleStatus.STARTING);

      final String SERVER_PORT = System.getProperty("server.port", "8080");
      final URI BASE_URI = URI.create(String.format("http://localhost:%s/", SERVER_PORT));
      final ResourceConfig config = createNewHttpResourceConfig();

      final Channel server = DualNettyServerProvider.createServer(BASE_URI, config, null);

      ApplicationLifecycleManager.registerListener(trans -> {
        if (trans.next == LifecycleStatus.TERMINATING) {
          server.close();
        }
      });

      log.info("Engagement Edge Platform started -> {}", BASE_URI);
      ApplicationLifecycleManager.updateStatus(LifecycleStatus.STARTED);
      Thread.currentThread().join();
    }

    protected static ResourceConfig createNewHttpResourceConfig() {

      ResourceConfig config = new ResourceConfig()
        .register(JerseyServerLogger.class)
        .register(HelloResource.class)
        .register(CORSResponseFilter.class)
        .register(JerseyExceptionMapper.class)
        .register(new AbstractBinder() {
          @Override
          protected void configure() {
            bind(HelloInboundProcessor.class).to(WebSocketInboundProcessor.class).in(Singleton.class);
            bind(DelayInboundProcessor.class).to(WebSocketInboundProcessor.class).in(Singleton.class);
          }
        });

      CommonConfiguration.basic(config);
      return config;
    }
  }


  @Test
  public void run() throws IOException {
    if (!new File("src/test/java").exists()) {
      throw new RuntimeException("Please ensure this test is run in the home directory of the project.");
    }

    // this ensures it understands we are in "dev" move, we aren't a bundled jar file - it also ensures
    // we get the test class loader
    new BatheBooter().runWithLoader(
      getClass().getClassLoader(),
      null,
      Main.class.getName(),
      new String[]{
        "-Pclasspath:/app.properties",
        "-P${user.home}/.webdev/app.properties"
      });
  }


}
