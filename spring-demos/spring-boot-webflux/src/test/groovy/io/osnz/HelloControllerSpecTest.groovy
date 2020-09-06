package io.osnz

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Title("HelloController Specification")
@Narrative("The Specification of the behaviour of the HelloController. It can greet a person, change the name and reset it to 'world'")
@ExtendWith(SpringExtension)
@WebFluxTest
class HelloControllerSpecTest extends Specification {

  @Autowired
  WebTestClient webTestClient

  def "when get is performed then the response has status 200 and content is 'Hello World'"() {
    expect: "Status is 200 and the response is 'Hello World'"
      webTestClient.get()
        .uri('/')
        .exchange()
        .expectStatus().isOk()
        .returnResult(String)
        .responseBody.blockFirst() == "Hello World"
  }

  @Unroll
  def 'URI #uri should return status code #code and response body #body'(String uri, int code, String body) {
    expect: "should works as expect"
      webTestClient.get()
        .uri(uri)
        .exchange()
        .expectStatus().isEqualTo(code)
        .returnResult(String)
        .responseBody.blockFirst() == body

    where:
      uri      | code | body
      "/"      | 200  | "Hello World"
      "/dummy" | 200  | "Hello dummy1 Hello dummy2 Hello dummy3 Hello dummy4 Hello dummy5 Hello dummy6 Hello dummy7 Hello dummy8 Hello dummy9 Hello dummy10 "
  }


}
