package co.connect.digit.processor;

import nz.co.asb.engagement.websocket.model.Request;
import nz.co.asb.engagement.websocket.processor.WebSocketInboundProcessor;

public class HelloInboundProcessor implements WebSocketInboundProcessor {

  @Override
  public boolean isSupportRequest(Request request) {
    return "hello".equals(request.getCommand());
  }

  @Override
  public String processRequest(Request request) {
    return "Response: " + request.getData().toUpperCase();
  }
}
