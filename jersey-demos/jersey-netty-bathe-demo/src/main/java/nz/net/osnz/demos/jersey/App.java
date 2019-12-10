package nz.net.osnz.demos.jersey;

import io.netty.channel.Channel;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
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
          bind(UserMessageBodyWriter.class).in(Singleton.class);
        }
      });

      resourceConfig.property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
      resourceConfig.property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
      resourceConfig.property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
      resourceConfig.property(ServletProperties.PROVIDER_WEB_APP, false); // do not scan!

      resourceConfig.register(JacksonFeature.class);
      resourceConfig.register(MultiPartFeature.class);
      resourceConfig.register(GZipEncoder.class);

      final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, resourceConfig, null);
      Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close()));
      LOG.info("Application is listening on {}{} (HTTP/2 enabled!)", BASE_URI, ROOT_PATH);

      try {
        UserApi.hello();
      } catch (Exception ex) {
        LOG.error("catch API exception : {}", ex.getMessage(), ex);
      }

      Thread.currentThread().join();

    } catch (InterruptedException ex) {
      LOG.error("Service is down", ex);
    }

  }

}
