package nz.co.asb.engagement.websocket.util;

import nz.co.asb.engagement.websocket.ChannelGroupManager;
import nz.co.asb.engagement.websocket.model.Client;
import nz.co.asb.engagement.websocket.service.MessageService;

/**
 * an Util class to process message, and manage channel group
 */
public class WebSocketUtil {

  private static final ChannelGroupManager CHANNEL_GROUP_MANAGER = new ChannelGroupManager();
  private static final MessageService MESSAGE_SERVICE = new MessageService(CHANNEL_GROUP_MANAGER);

  private WebSocketUtil() {
    // cannot be instantiated
  }

  public static void registerClient(Client client) {
    CHANNEL_GROUP_MANAGER.put(client.getTopic(), client.getClientId(), client.getChannel());
  }

  public static void deregisterClient(Client client) {
    CHANNEL_GROUP_MANAGER.remove(client.getTopic(), client.getClientId(), client.getChannel());
  }

  public static void broadcast(Client client, String message) {
    MESSAGE_SERVICE.broadcast(client.getTopic(), client.getClientId(), message);
  }

  public static String summary() {
    return CHANNEL_GROUP_MANAGER.toString();
  }


}
