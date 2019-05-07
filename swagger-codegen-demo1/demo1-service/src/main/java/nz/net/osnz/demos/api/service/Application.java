package nz.net.osnz.demos.api.service;

import io.netty.channel.Channel;
import nz.net.osnz.demo.swagger.api.CustomerApi;
import nz.net.osnz.demos.api.CustomersService;
import org.glassfish.hk2.api.Immediate;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.core.Context;
import java.net.URI;

import static org.glassfish.jersey.server.monitoring.ApplicationEvent.Type.INITIALIZATION_FINISHED;


/**
 * @author Kefeng Deng @ 2019-04-20 20:51
 */
public class Application {

  private static final Logger LOG = LoggerFactory.getLogger(Application.class);

  public static final URI BASE_URI = URI.create("http://localhost:8080/");

  public static void main(String[] args) {
    try {
      LOG.info("Starting jersey on netty container");

      final ResourceConfig resourceConfig = new ResourceConfig()
        .register(CustomerApi.class)
//        .register(MainApplicationListener.class)
//        .register(ServletContextClass.class)
        .registerInstances(new AbstractBinder() {
          @Override
          protected void configure() {
//            ServiceFactory factory = new ServiceFactory();
//            ApiClient client = new ApiClient();
//            CustomersApi customersApi = new CustomersApi(client);
//            bind(customersApi).to(CustomersApi.class);
          }
        })
        .register(new AbstractBinder() {
          @Override
          protected void configure() {
            bind(ServiceFactory.class).to(ServiceFactory.class).in(Immediate.class);
            bind(CustomersService.class).to(CustomersService.class).in(Singleton.class);
//          bind(JacksonFeature.class).to(JacksonFeature.class).in(Singleton.class);
//          bind(UserMessageBodyWriter.class).in(Singleton.class);
//            bind(ServiceFactory.class).to(ServiceFactory.class).in(Singleton.class);
          }
        });

      resourceConfig.getProperties().put("CustomerApi.implementation", "nz.net.osnz.demos.api.CustomersService");


      final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, resourceConfig, null);

      Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close()));
      LOG.info("Application started. (HTTP/2 enabled!)\nTry out {}\nStop the application using CTRL+C.", BASE_URI);

      Thread.currentThread().join();

    } catch (InterruptedException ex) {
      LOG.error("Service is down", ex);
    }

  }
//
//  private static class MainApplicationListener implements ApplicationEventListener {
//
//    @Context
//    private ServletContext ctx;
//
//    @Override
//    public void onEvent(ApplicationEvent event) {
//      switch (event.getType()) {
//        case INITIALIZATION_FINISHED:
//          // do whatever you want with your ServletContext ctx
//          ctx.setInitParameter("CustomerApi.implementation", "nz.net.osnz.demos.api.CustomersService");
//          break;
//      }
//    }
//
//    @Override
//    public RequestEventListener onRequest(RequestEvent requestEvent) {
//      return null;
//    }
//  }
//
//  public static class ServletContextClass implements ServletContextListener {
//
//    public void contextInitialized(ServletContextEvent arg0) {
//      //use the ServletContextEvent argument to access the
//      //parameter from the context-param
//      String my_param = arg0.getServletContext().getInitParameter("your_param");
//    }//end contextInitialized method
//
//    @Override
//    public void contextDestroyed(ServletContextEvent arg0)
//    { }//end constextDestroyed method
//  }


}
