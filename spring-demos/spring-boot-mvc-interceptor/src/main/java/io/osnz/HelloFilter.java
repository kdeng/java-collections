package io.osnz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
@Order(1)
@Slf4j
public class HelloFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.info("Step in HelloFilter");
    request.setAttribute("name", "new zealand");
    chain.doFilter(request, response);
  }

}
