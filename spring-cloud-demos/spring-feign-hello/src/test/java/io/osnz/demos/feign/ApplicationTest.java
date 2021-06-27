package io.osnz.demos.feign;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private HelloClient helloClient;

  @Test
  public void shouldReturnCorrectResponseBody() {
    String body = restTemplate.getForObject("/", String.class);
    Assertions.assertThat(body).isEqualTo("Hello world");
  }

  @Test
  public void shouldReturnUpStatus() {
    HttpEntity<String> request = new HttpEntity<String>("aaa", new LinkedMultiValueMap<>());
    String body = restTemplate.postForObject("/", request, String.class);
    Assertions.assertThat(body).isEqualTo("Hello aaa");
  }

  @Test
  public void shouldInvokeClientProperly() {
    Assertions.assertThat(helloClient.hello()).isEqualTo("Hello world");
  }

}
