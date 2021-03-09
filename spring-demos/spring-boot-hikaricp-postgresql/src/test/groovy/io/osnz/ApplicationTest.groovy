package io.osnz


import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringRunner)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApplicationTest {

  @Autowired
  TestRestTemplate restTemplate

  @Test
  void shouldReturnCorrectResponseBody() {
    def body = (List<User>) restTemplate.getForObject('/aaa', List)
    body == [
      [id: 1, name: 'aaa', email: 'aaa@aaa'] as User,
      [id: 2, name: 'aaa', email: 'bbb@aaa'] as User,
      [id: 3, name: 'aaa', email: 'ccc@aaa'] as User,
      [id: 4, name: 'aaa', email: 'ddd@aaa'] as User,
      [id: 4, name: 'aaa', email: 'eee@aaa'] as User
    ]
  }

  @Test
  public void shouldReturnUpStatus() {
    def body = this.restTemplate.getForObject("/actuator/health", String.class)
    Assert.assertEquals("{\"status\":\"UP\"}", body);
  }

}
