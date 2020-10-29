package io.osnz.demos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "helloClient", url = "${hello-service.url}")
public interface HelloClient {

  @PostMapping()
  String sayHello(@RequestBody String name);

  @GetMapping()
  String hello();

}
