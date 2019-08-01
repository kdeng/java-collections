package io.osnz.demos.jersey.feature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class UserResource {

  private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

  public UserResource() {
    LOG.info("Instantiated UserResource");
  }

  @Inject
  private UserService userService;

  @GET
  public String hello() {
    return "Hello World";
  }

  @GET
  @Path("/{id}")
  public String name(@PathParam("id") String id) {
    return "Response to : " +  userService.findNameById(id);
  }

}
