package io.osnz

import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringRunner)
@WebFluxTest
class HelloControllerTest {

  @Autowired
  WebTestClient webTestClient

  @Test
  void shouldReturnHelloWorld() {
    webTestClient.get()
      .uri('/')
      .exchange()
      .expectStatus().isOk()
      .returnResult(String)
      .responseBody.single()
      .block() == "Hello World"
  }

  @Test
  void 'should say hello properly'() {
    webTestClient.get()
      .uri('/dummy')
      .exchange()
      .expectStatus().isOk()
      .returnResult(String)
      .responseBody.single()
      .block() == 'Hello dummy1 Hello dummy2 Hello dummy3 Hello dummy4 Hello dummy5 Hello dummy6 Hello dummy7 Hello dummy8 Hello dummy9 Hello dummy10 '
  }

}
