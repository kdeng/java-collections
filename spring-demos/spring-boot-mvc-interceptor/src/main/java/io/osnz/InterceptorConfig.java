package io.osnz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
@RequiredArgsConstructor
public class InterceptorConfig extends WebMvcConfigurationSupport {

  private final HelloInterceptor helloInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(helloInterceptor);
  }

}
