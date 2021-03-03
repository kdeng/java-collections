package io.osnz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
@Slf4j
public class MyRestTemplate extends RestTemplate {

  public MyRestTemplate() {
    super();
    addInterceptors();
  }

  public void addInterceptors() {
    final List<ClientHttpRequestInterceptor> interceptorList = getInterceptors();
    interceptorList.add(newInterceptor());
    setInterceptors(interceptorList);
  }

  private ClientHttpRequestInterceptor newInterceptor() {
    return (HttpRequest request, byte[] body, ClientHttpRequestExecution execution) -> {
      log.info("Step in MyRestTemplate interceptor");
      return execution.execute(new MyRequestWrapper(request), body);
    };
  }

  private class MyRequestWrapper extends HttpRequestWrapper {

    public MyRequestWrapper(HttpRequest request) {
      super(request);
    }

    @Override
    public URI getURI() {
      try {
        return new URI(super.getURI().toString() + "-modified");
      } catch (URISyntaxException e) {
        throw new RuntimeException(e);
      }
    }
  }


}
