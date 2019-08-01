package nz.co.asb.engagement.websocket.service;

import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import nz.co.asb.engagement.websocket.ChannelGroupManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Message service to send a message to a channel group
 */
public class MessageService {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final ChannelGroupManager channelManager;

  public MessageService(ChannelGroupManager channelGroupManager) {
    this.channelManager = channelGroupManager;
  }

  public void broadcast(String topic, String clientId, String message) {

    log.debug("Trying to send a message to topic '{}' for client ID '{}'", topic, clientId);

    ChannelGroup channelGroup = channelManager.get(topic, clientId);

    if (channelGroup == null) {
      log.debug("No connection to subscribe topic '{}' for client ID '{}'", topic, clientId);
      return;
    }

    channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    log.debug("Successfully sent the message to topic '{}' for client ID '{}'", topic, clientId);

  }

}
