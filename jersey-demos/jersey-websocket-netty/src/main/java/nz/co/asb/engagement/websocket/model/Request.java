package nz.co.asb.engagement.websocket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Request DTO describes the request stream data structure
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Request {

  private String command;
  private String data;

}
