package io.osnz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RestControllerAdvice
@Slf4j
public class MyRequestAdviceAdapter extends RequestBodyAdviceAdapter {

  @Override
  public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                              Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    log.info("Request body => {}", body);
    return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
  }


}
