package nz.net.osnz.demos.j2ee

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringRunner)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyServletTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testHomeEndpoint() {
    String body = this.restTemplate.getForObject("/home", String.class)
    Assert.assertEquals("Hello world", body)
  }

}
