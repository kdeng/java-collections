package io.osnz.demos.netty;

import io.osnz.demos.netty.api.HelloService;

/**
 * @author Kefeng Deng
 * @since 2019-05-13
 */
public class HelloResourceApi implements HelloService {
  @Override
  public String hello(String name) {
    return "Hello " + (name == null ? "world" : name);
  }
}
