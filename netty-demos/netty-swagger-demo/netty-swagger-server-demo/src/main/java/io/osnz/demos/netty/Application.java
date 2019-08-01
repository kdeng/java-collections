package io.osnz.demos.netty;

import cd.connect.jersey.common.CommonConfiguration;
import cd.connect.jersey.common.JerseyExceptionMapper;
import cd.connect.lifecycle.ApplicationLifecycleManager;
import cd.connect.lifecycle.LifecycleStatus;
import cd.connect.openapi.support.ApiClient;
import io.netty.channel.Channel;
import io.osnz.demos.netty.api.HelloClientService;
import io.osnz.demos.netty.api.HelloService;
import io.osnz.demos.netty.api.impl.HelloServiceImpl;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.logging.JerseyClientLogger;
import org.glassfish.jersey.logging.JerseyServerLogger;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URI;

public class Application {

  private static final Logger LOG = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {

    ApplicationLifecycleManager.updateStatus(LifecycleStatus.STARTING);

    final URI BASE_URI = URI.create(String.format("http://localhost:%s/", System.getProperty("server.port", "9090")));
    final ResourceConfig config = initResourceConfig();

    CommonConfiguration.basic(config);

    final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, config, null);

    ApplicationLifecycleManager.registerListener(trans -> {
      if (trans.next == LifecycleStatus.TERMINATING) {
        server.close();
      }
    });

    LOG.info("Hello API Application started. (HTTP/2 enabled!) -> {}", BASE_URI);

    ApplicationLifecycleManager.updateStatus(LifecycleStatus.STARTED);
    Thread.currentThread().join();
  }

  protected static ResourceConfig initResourceConfig() {
    Client client = createJerseyClient();

    return new ResourceConfig()
      .register(JerseyServerLogger.class)
      .register(HelloResourceApi.class)
      .register(ProxyApi.class)
      .register(JerseyExceptionMapper.class)
      .registerInstances(new AbstractBinder() {
        @Override
        protected void configure() {
          ApiClient apiClient = new ApiClient(client);

          HelloServiceImpl helloService = new HelloServiceImpl(apiClient);

          bind(helloService).to(HelloService.class).in(Singleton.class);
          bind(helloService).to(HelloClientService.class).in(Singleton.class);
        }
      });
  }

  private static Client createJerseyClient() {
    final ClientBuilder clientBuilder = ClientBuilder.newBuilder();
    CommonConfiguration.basic(clientBuilder);
    clientBuilder.register(JerseyClientLogger.class);
    return clientBuilder.build();
  }

}
