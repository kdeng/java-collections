package nz.co.asb.engagement.websocket;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * This class manages a group of channel group for all websocket connections
 */
public class ChannelGroupManager {

  private static ConcurrentHashMap<String, ConcurrentHashMap<String, ChannelGroup>> map = new ConcurrentHashMap<>();

  /**
   * Add a new client channel into {@link ChannelGroup}
   *
   * @param topic      is a topic of channel
   * @param clientId   is the unique client ID
   * @param newChannel is the incoming channel
   */
  public void put(String topic, String clientId, Channel newChannel) {
    map
      .computeIfAbsent(topic, v -> new ConcurrentHashMap<>())
      .computeIfAbsent(clientId, v -> new DefaultChannelGroup(GlobalEventExecutor.INSTANCE))
      .add(newChannel);
  }

  /**
   * Get {@link ChannelGroup} from manager
   *
   * @param topic    is the topic of channel
   * @param clientId is the client ID
   * @return {@link ChannelGroup} if the channel group already exists
   */
  public ChannelGroup get(String topic, String clientId) {
    ConcurrentHashMap<String, ChannelGroup> groupMap = map.get(topic);
    return groupMap == null ? null : groupMap.get(clientId);
  }

  /**
   * Remove a channel from {@link ChannelGroup}
   *
   * @param topic
   * @param clientId
   * @param channel
   */
  public void remove(String topic, String clientId, Channel channel) {
    map
      .computeIfAbsent(topic, v -> new ConcurrentHashMap<>())
      .computeIfAbsent(clientId, v -> new DefaultChannelGroup(GlobalEventExecutor.INSTANCE))
      .remove(channel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("[total channels:%s]", map.mappingCount()));
    int subscribers = map.values().stream()
      .map(ConcurrentHashMap::values)
      .flatMap(x -> x.stream())
      .mapToInt(x -> x.size())
      .sum();
    sb.append(String.format("[total subscriber:%s]", subscribers));
    return sb.toString();
  }

}
