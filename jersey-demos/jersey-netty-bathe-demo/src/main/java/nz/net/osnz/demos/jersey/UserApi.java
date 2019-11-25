package nz.net.osnz.demos.jersey;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserApi {

  private final HelloService helloService;

  @Inject
  public UserApi(HelloService helloService) {
    this.helloService = helloService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUser() {
    return Response.ok().entity(User.builder().name("bobo").greeting(helloService.getHello()).build()).build();
  }

}
