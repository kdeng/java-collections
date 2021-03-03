package io.osnz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class HelloService {

  private final MyRestTemplate restTemplate;

  public String sayHello(String url, String name) {
    return restTemplate.getForObject(url + "/" + name, String.class);
  }

}
