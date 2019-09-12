package nz.net.osnz.demos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@WebFluxTest
@Import(Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureJsonTesters
public class ApplicationFluxTest {

  @Autowired
  private Application.Handler handler;

  @Autowired
  private Application webConfig;

  @Test
  public void testSubscribeMono() {
    StepVerifier.create(WebTestClient
      .bindToRouterFunction(webConfig.monoRouterFunction(handler))
      .build()
      .get().uri("/handler/mono")
      .accept(MediaType.TEXT_PLAIN)
      .exchange()
      .returnResult(String.class)
      .getResponseBody()
    )
//      .expectSubscription()
      .expectNext("MONO")
      .expectComplete()
      .verify();
  }

  @Test
  public void testSubscribeFlux() {
    StepVerifier.create(WebTestClient
      .bindToRouterFunction(webConfig.monoRouterFunction(handler))
      .build()
      .get().uri("/handler/flux")
      .accept(MediaType.TEXT_PLAIN)
      .exchange()
      .returnResult(String.class)
      .getResponseBody()
    )
//      .expectSubscription()
      .expectNext("FLUX")
      .expectComplete()
      .verify();
  }

}
