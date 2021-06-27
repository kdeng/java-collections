package io.osnz.demos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "helloClient", url = "${hello-service.url}")
public interface HelloClient {

  @PostMapping()
  String sayHello(@RequestBody String name);

  @GetMapping()
  String hello();

  @GetMapping("/exception/single")
  String singleException();

  @GetMapping("/exception/response")
  ResponseEntity<Map> multipleException();


}
