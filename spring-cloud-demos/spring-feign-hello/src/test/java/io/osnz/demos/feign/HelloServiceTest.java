package io.osnz.demos.feign;

import io.osnz.demos.feign.service.HelloService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloServiceTest {

  @Autowired
  private HelloService helloService;

  @Test
  public void shouldInvokeClientProperly() {
    Assert.assertEquals("Hello world", helloService.invokeHello());
  }

  @Test
  public void shouldInvokeSayHelloProperly() {
    Assert.assertEquals("Hello aaa", helloService.invokeSayHello("aaa"));
  }

}
