package nz.co.asb.engagement.websocket.processor;

import nz.co.asb.engagement.websocket.model.Client;
import nz.co.asb.engagement.websocket.model.Request;
import nz.co.asb.engagement.websocket.util.WebSocketUtil;

/**
 * Asynchronous web socket processor
 */
public abstract class WebSocketInboundFutureProcessor implements WebSocketInboundProcessor {

  public void futureProcess(Client client, Request request) {
    Runnable task = () -> {
      WebSocketUtil.broadcast(client, processRequest(request));
    };
    new Thread(task).start();
  }

}
