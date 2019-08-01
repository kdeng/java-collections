package io.osnz.demos.rxjersey.provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Kefeng Deng (kefeng.deng@clearpoint.co.nz)
 */
public class JacksonObjectProvider {

  public static ObjectMapper mapper;

  static {
    mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

}
