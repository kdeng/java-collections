package nz.co.asb.engagement.websocket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.netty.channel.Channel;
import lombok.Data;


/**
 * Client object describes all client details
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {

  /**
   * Current client uid
   */
  private String clientId;
  private String topic;
  private String token;
  private Channel channel;

}
