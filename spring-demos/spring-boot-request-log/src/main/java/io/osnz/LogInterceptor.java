package io.osnz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.http.HttpMethod;
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
public class LogInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler) {

    if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
      && request.getMethod().equals(HttpMethod.GET.name())) {
      log.info("Get request => {}", request);
    }

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

  }

}
