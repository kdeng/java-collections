package co.connect.digit.processor;

import nz.co.asb.engagement.websocket.model.Request;
import nz.co.asb.engagement.websocket.processor.WebSocketInboundFutureProcessor;

/**
 * @author Kefeng Deng
 * @since 2019-05-01
 */
public class DelayInboundProcessor extends WebSocketInboundFutureProcessor {

  @Override
  public String processRequest(Request request) {
    try {
      Thread.sleep(5 * 1000);
    } catch (InterruptedException ex) {
    }
    return "Delay 5 seconds : " + request.getData().toUpperCase();
  }

  @Override
  public boolean isSupportRequest(Request request) {
    return "delay".equals(request.getCommand());
  }

}
