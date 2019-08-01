package io.osnz.demos.rxjersey.provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;

/**
 * @author Kefeng Deng (kefeng.deng@clearpoint.co.nz)
 */
public class JacksonContextProvider implements ContextResolver<ObjectMapper> {
  @Override
  public ObjectMapper getContext(Class<?> aClass) {
    return JacksonObjectProvider.mapper;
  }

}
