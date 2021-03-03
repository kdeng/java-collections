package io.osnz;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private HelloService helloService;

  @LocalServerPort
  private int port;

  @Test
  public void shouldReturnUpStatus() {
    String body = this.restTemplate.getForObject("/actuator/health", String.class);
    Assert.assertEquals("{\"status\":\"UP\"}", body);
  }

  @Test
  public void shouldMutateRequestURIProperly() {
    String baseUrl = "http://localhost:" + port;
    String response = helloService.sayHello(baseUrl, "asd");
    Assertions.assertThat(response).isEqualTo("Hello asd-modified, new zealand");
  }


}
