package nz.co.asb.engagement.websocket.processor;

import nz.co.asb.engagement.websocket.model.Request;

/**
 * Abstract WebSocket Inbound Processor to consume incoming command from web socket connection
 */
public interface WebSocketInboundProcessor {

  /**
   * Check whether current WebSocketService can consume this incoming request
   *
   * @param request is a {@link Request} to describe the incoming request stream data
   * @return with {@code true}, it means current service can process this request
   */
  boolean isSupportRequest(Request request);

  /**
   * Process the incoming request stream
   *
   * @param request is a {@link Request} object
   * @return a serialised response in {@link String} format
   */
  String processRequest(Request request);

}
