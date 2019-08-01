package co.connect.digit.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api")
public class HelloResource {

  @GET
  @Path("/hello")
  public String hello() {
    return "Hello world";
  }

}
