package io.osnz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
@Slf4j
public class HelloInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    log.info("Step in preHandle");
    request.setAttribute("name", "australia1");
    response.setHeader("x-response", "hello1");
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    log.info("Step in afterCompletion");
    response.setHeader("x-after-completion", "hello1");
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    log.info("Step in postHandle");
    response.setHeader("x-interceptor-response", "hello1");
  }
}
