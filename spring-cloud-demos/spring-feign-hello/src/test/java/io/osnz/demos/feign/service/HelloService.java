package io.osnz.demos.feign.service;

import io.osnz.demos.feign.HelloClient;
import nz.net.osnz.common.jackson.JacksonHelper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class HelloService {

  private final HelloClient helloClient;

  public HelloService(HelloClient helloClient) {
    this.helloClient = helloClient;
  }

  public String invokeHello() {
    return helloClient.hello();
  }

  public String invokeSayHello(String name) {
    return helloClient.sayHello(name);
  }

  public List<String> invokeException() {
    try {
      helloClient.singleException();
    } catch (feign.FeignException feignException) {
      String error = feignException.contentUTF8();
      return Collections.singletonList(error);
    }
    return Collections.emptyList();
  }

  public Map<String, Object> invokeExceptions() {
    try {
      helloClient.multipleException();
    } catch (feign.FeignException feignException) {
      return JacksonHelper.deserialize(feignException.contentUTF8(), Map.class);
    }

    return Collections.emptyMap();
  }

}
