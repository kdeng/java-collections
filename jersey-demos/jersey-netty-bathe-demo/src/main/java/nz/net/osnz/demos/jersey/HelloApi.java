package nz.net.osnz.demos.jersey;

import cd.connect.app.config.ConfigKey;
import cd.connect.app.config.DeclaredConfigResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("hello")
@Singleton
public class HelloApi {

  private static final Logger LOG = LoggerFactory.getLogger(HelloApi.class);

  private final HelloService helloService;

  @ConfigKey("url")
  private String url;

  @Inject
  public HelloApi(HelloService helloService) {
    DeclaredConfigResolver.resolve(this);
    this.helloService = helloService;
    LOG.info("Url : {}", url);
  }

  @GET
  @Produces("text/plain")
  public String getHello() {
    LOG.info("Received a GET request for hello");
    return helloService.getHello();
  }

  @POST
  @Produces("text/plain")
  public String postHello() {
    LOG.info("Received a POST request for hello");
    return helloService.getHello("world for POST request");
  }


}
