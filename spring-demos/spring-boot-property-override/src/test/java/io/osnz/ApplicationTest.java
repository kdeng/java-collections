package io.osnz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"dev", "test"})
public class ApplicationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void shouldReturnCorrectResponseBody() {
    String body = this.restTemplate.getForObject("/", String.class);
    Assert.assertEquals("myName : test : test", body);
  }

  @Test
  public void shouldReturnUpStatus() {
    String body = this.restTemplate.getForObject("/actuator/health", String.class);
    Assert.assertEquals("{\"status\":\"UP\"}", body);
  }

}
