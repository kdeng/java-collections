package io.osnz.demos.rxjersey.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Path("/user")
public class UserResource {
  private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Integer> getUsers() {
    try {
      Thread.sleep(10 * 1000);
      LOG.info("Returning list");
      return Arrays.asList(1, 2, 3, 4, 5);
    } catch (InterruptedException ex) {

    }

    LOG.info("return empty list");
    return Collections.emptyList();
  }

}
