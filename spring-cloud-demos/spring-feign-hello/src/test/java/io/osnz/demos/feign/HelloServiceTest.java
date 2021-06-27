package io.osnz.demos.feign;

import io.osnz.demos.feign.service.HelloService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloServiceTest {

  @Autowired
  private HelloService helloService;

  @Test
  public void shouldInvokeClientProperly() {
    Assertions.assertThat(helloService.invokeHello()).isEqualTo("Hello world");
  }

  @Test
  public void shouldInvokeSayHelloProperly() {
    Assertions.assertThat(helloService.invokeSayHello("aaa")).isEqualTo("Hello aaa");
  }

  @Test
  public void shouldGetSingleException() {
    List<String> response = helloService.invokeException();

    Assertions.assertThat(response.size()).isEqualTo(1);
    Assertions.assertThat(response.get(0).contains("Internal Server Error")).isTrue();
  }

  @Test
  public void shouldGetErrorResponse() {
    Map<String, Object> response = helloService.invokeExceptions();

    Assertions.assertThat(response.size()).isEqualTo(2);
    Assertions.assertThat(response.get("code")).isEqualTo(400);
    Assertions.assertThat(response.get("error")).isEqualTo("bad request");
  }


}
