package io.osnz;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
//@RunWith(SpringRunner.class) //Disable this to use Junit 5
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

  TestRestTemplate restTemplate;
  URL base;
  @LocalServerPort
  int port;

  @ParameterizedTest
  @MethodSource("provideValidCredentials")
  public void whenLoggedWithCorrectCredentials_ThenSuccess(String username, String password, String path, String expectedBody) throws IllegalStateException, IOException {
    // Given
    if (username == null) {
      restTemplate = new TestRestTemplate();
    } else {
      restTemplate = new TestRestTemplate(username, password);
    }

    base = new URL("http://localhost:" + port + path);

    // When
    ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

    // Then
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(expectedBody.equals(response.getBody()));
  }

  static private Stream<Arguments> provideValidCredentials() {
    return Stream.of(
      Arguments.of(null, null, "/", "Hello World"),
      Arguments.of("vip", "vip", "/hello", "say hello"),
      Arguments.of("user", "user", "/hello", "say hello"),
      Arguments.of("vip", "vip", "/vip", "say vip"),
      Arguments.of("user", "user", "/user", "say user"),
      Arguments.of("admin", "admin", "/admin", "say admin")
    );
  }


  @ParameterizedTest
  @MethodSource("provideWrongCredentials")
  public void whenUserWithWrongCredentials_thenUnauthorizedPage(String username, String password, String path) throws Exception {
    // Given
    restTemplate = new TestRestTemplate(username, password);
    base = new URL("http://localhost:" + port + path);

    // When
    ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

    // Then
//    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

  static private Stream<Arguments> provideWrongCredentials() {
    return Stream.of(
      Arguments.of("vip", "vip", "/admin"),
      Arguments.of("vip", "vip", "/user"),
      Arguments.of("user", "user", "/admin"),
      Arguments.of("admin", "admin", "/vip")
    );
  }

}
