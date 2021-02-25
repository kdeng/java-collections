package io.osnz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(AspectConfig.class)
public class ApplicationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void shouldReturnCorrectResponseBody() {
    String body = this.restTemplate.getForObject("/world", String.class);
    Assert.assertEquals("Hello world", body);
  }

  @Test
  public void shouldReturnUpStatus() {
    String body = this.restTemplate.getForObject("/actuator/health", String.class);
    Assert.assertEquals("{\"status\":\"UP\"}", body);
  }

}
