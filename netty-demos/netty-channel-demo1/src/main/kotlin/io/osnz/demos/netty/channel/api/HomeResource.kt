package io.osnz.demos.netty.channel.api

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 *
 * @author Kefeng Deng
 * @since   2019-05-09
 */
@Path("/")
class HomeResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  fun home(): String = "Welcome to homepage"

  @POST
  fun update(): String = "Update home"

}
