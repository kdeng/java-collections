package io.osnz.demos.feign.service;

import io.osnz.demos.feign.HelloClient;
import org.springframework.stereotype.Service;

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

}
