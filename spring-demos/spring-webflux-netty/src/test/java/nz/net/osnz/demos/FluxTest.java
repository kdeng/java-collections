package nz.net.osnz.demos;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
public class FluxTest {
  @Test
  public void testMono() {
    StepVerifier.create(Mono.just(1))
      .expectNext(1)
      .expectNextCount(0)
      .expectComplete()
      .verify();
  }

}
