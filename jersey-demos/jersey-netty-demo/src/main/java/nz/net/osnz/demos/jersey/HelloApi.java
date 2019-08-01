package nz.net.osnz.demos.jersey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("hello")
public class HelloApi {

  private static final Logger LOG = LoggerFactory.getLogger(HelloApi.class);

  private final HelloService helloService;

  @Inject
  public HelloApi(HelloService helloService) {
    this.helloService = helloService;
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
