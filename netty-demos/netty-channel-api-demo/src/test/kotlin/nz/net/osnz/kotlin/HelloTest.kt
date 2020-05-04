package nz.net.osnz.kotlin

import io.osnz.demos.netty.channel.api.HomeResource
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.junit.Test
import org.junit.jupiter.api.Assertions
import javax.ws.rs.core.Application
import javax.ws.rs.core.Response


class HelloTest : JerseyTest() {

  override fun configure(): Application {
    return ResourceConfig().register(HomeResource::class.java)
  }

//  private val target: WebTarget = ClientBuilder.newClient().target(App.APP_HOST)

  @Test
  fun hello() {
    val response = target("/").request().get()
    Assertions.assertEquals(Response.Status.OK.statusCode, response.status)
    Assertions.assertEquals("Welcome to homepage", response.readEntity(String::class.java))
  }
}
