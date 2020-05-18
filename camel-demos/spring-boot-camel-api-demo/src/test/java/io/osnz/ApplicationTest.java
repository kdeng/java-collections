package io.osnz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void shouldReturnCorrectResponseBodyInGet() {
    Greeting body = this.restTemplate.getForObject("/camel/api/hello/World", Greeting.class);
    Assert.assertEquals("Hello World", body.name);
    Assert.assertTrue(1000 == body.id);
  }

  @Test
  public void shouldReturnCorrectResponseBodyInPost() {
    Greeting requestBody = Greeting.builder().id(1).name("world").build();
    String body = this.restTemplate.postForObject("/camel/api/bean", requestBody, String.class);
    Assert.assertEquals("Hello world", body);
  }

}
