package io.osnz.demos.feign;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private HelloClient helloClient;

  @Test
  public void shouldReturnCorrectResponseBody() {
    String body = restTemplate.getForObject("/", String.class);
    Assert.assertEquals("Hello world", body);
  }

  @Test
  public void shouldReturnUpStatus() {
    HttpEntity<String> request = new HttpEntity<String>("aaa", new LinkedMultiValueMap<>());
    String body = restTemplate.postForObject("/", request, String.class);
    Assert.assertEquals("Hello aaa", body);
  }

  @Test
  public void shouldInvokeClientProperly() {
    Assert.assertEquals("Hello world", helloClient.hello());
  }

}
