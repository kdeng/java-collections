package io.osnz.demos.feign;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kdeng
 */
@RestController
@RequestMapping("/")
public class HelloController implements HelloClient {

  @Override
  public String sayHello(String name) {
    return "Hello " + name;
  }

  @Override
  public String hello() {
    return "Hello world";
  }

  @Override
  public String singleException() {
    throw new ApiException("error");
  }

  @Override
  public ResponseEntity<Map> multipleException() {
    Map<String, Object> map = new HashMap<>();
    map.put("error", "bad request");
    map.put("code", HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
  }
}
