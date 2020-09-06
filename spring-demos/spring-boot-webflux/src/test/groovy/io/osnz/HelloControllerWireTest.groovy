package io.osnz

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient

/**
 * @author Kefeng Deng (deng@51any.com)
 */
class HelloControllerWireTest {

  WireMockServer wireMockServer
  WebClient webClient

  @BeforeEach
  void setUp() throws Exception {
    wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
    wireMockServer.start();
    webClient = WebClient.builder().baseUrl(wireMockServer.baseUrl()).build();
  }

  @AfterEach
  void setDown() throws Exception {
    wireMockServer.stop()
  }

  @Test
  void shouldReturnOK() {

    wireMockServer
      .stubFor(WireMock.get('/').willReturn(WireMock.ok('OK')));

    String body = webClient.get()
      .uri('/')
      .retrieve()
      .bodyToMono(String)
      .block()

    'OK' == body
  }

}
