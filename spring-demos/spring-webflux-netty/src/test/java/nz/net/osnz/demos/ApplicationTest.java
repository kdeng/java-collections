package nz.net.osnz.demos;

import nz.net.osnz.demos.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebFluxTest

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@WebFluxTest
public class ApplicationTest {

  @Autowired
  private ApplicationContext context;

  private WebTestClient webClient;

  @Before
  public void setup() {
    webClient = WebTestClient
      .bindToApplicationContext(context)
      .configureClient()
      .baseUrl("http://localhost:9090")
      .build();
  }

  @Test
  public void getHandlerFlux() throws Exception {
    webClient.get().uri("/handler/flux")
      .exchange()
      .expectStatus().isOk()
      .expectBody(String.class)
      .isEqualTo("FLUX");
  }

  @Test
  public void getHandlerMono() throws Exception {
    webClient.get().uri("/handler/mono")
      .exchange()
      .expectStatus().isOk()
      .expectBody(String.class)
      .isEqualTo("MONO");
  }

  @Test
  public void getControllerMono() throws Exception {
    webClient.get().uri("/controller/mono")
      .exchange()
      .expectStatus().isOk()
      .expectBody(String.class)
      .isEqualTo("mono");
  }

  @Test
  public void getControllerFlux() throws Exception {
    webClient.get().uri("/controller/flux")
      .exchange()
      .expectStatus().isOk()
      .expectBody(String.class)
      .isEqualTo("flux");
  }


}
