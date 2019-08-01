package nz.co.asb.engagement.websocket.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * A response DTO describes the response data structure
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

  /**
   * with {@value (200)} means a successful response
   * non-zero value means unsuccessful response, and {@link #error} will be a valid value.
   */
  @Builder.Default
  private int code = 200;

  @Builder.Default
  private final long timestamp = System.currentTimeMillis();

  private final String clientId;
  private final String topic;
  private final String data;
  private final String error;

}
