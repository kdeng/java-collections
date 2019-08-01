package nz.net.osnz.java;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void shouldReturnCorrectResponseBody() {
    String body = this.restTemplate.getForObject("/", String.class);
    Assertions.assertThat(body).isEqualTo("Hello World");
  }

  @Test
  @DisplayName("Requires login for accessing '/home'")
  public void shouldBeBlocked() {
    String response = this.restTemplate.getForObject("/home", String.class);
    Assertions.assertThat(response).contains("Please sign in");
  }


}
