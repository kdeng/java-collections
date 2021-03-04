package io.osnz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
@Slf4j
@Order(2)
public class Hello2Filter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.info("Step in Hello2Filter");
    request.setAttribute("name", "new zealand2");
    HttpServletResponse myResponse = (HttpServletResponse) response;
    MyResponseRequestWrapper responseWrapper = new MyResponseRequestWrapper(myResponse);
    responseWrapper.setHeader("x-my-response", "new zealand2");
    chain.doFilter(request, myResponse);
  }

  public class MyResponseRequestWrapper extends HttpServletResponseWrapper {
    public MyResponseRequestWrapper(HttpServletResponse response) {
      super(response);
    }
  }

}
