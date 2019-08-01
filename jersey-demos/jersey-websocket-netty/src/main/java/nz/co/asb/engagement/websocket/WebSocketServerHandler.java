package nz.co.asb.engagement.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import nz.co.asb.engagement.websocket.model.Client;
import nz.co.asb.engagement.websocket.model.Request;
import nz.co.asb.engagement.websocket.model.Response;
import nz.co.asb.engagement.websocket.processor.WebSocketInboundFutureProcessor;
import nz.co.asb.engagement.websocket.processor.WebSocketInboundProcessor;
import nz.co.asb.engagement.websocket.service.RequestService;
import nz.co.asb.engagement.websocket.util.WebSocketUtil;
import nz.net.osnz.common.jackson.JacksonException;
import nz.net.osnz.common.jackson.JacksonHelper;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.inject.hk2.ImmediateHk2InjectionManager;
import org.glassfish.jersey.netty.httpserver.EdgeNettyHttpContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.HOST;
import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Main WebSocket Server Handler
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private static final String WEBSOCKET_BASE_PATH = "/ws";

  private static final String HTTP_REQUEST_TOKEN = "token";

  private final List<WebSocketInboundProcessor> processorList;

  private Client client;

  private WebSocketServerHandshaker handshaker;

  public WebSocketServerHandler(EdgeNettyHttpContainer container) {
    super();
    ServiceLocator serviceLocator = ((ImmediateHk2InjectionManager) container.getApplicationHandler().getInjectionManager()).getServiceLocator();
    processorList = serviceLocator.getAllServices(WebSocketInboundProcessor.class);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
    log.debug("Fired web socket server handler");
    if (msg instanceof FullHttpRequest) {
      final FullHttpRequest req = (FullHttpRequest) msg;

      if (isNotWebSocketPath(req)) {
        ctx.fireChannelRead(msg);
        return;
      }

      if (isIgnoreRequest(req)) {
        sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND));
        return;
      }

      handleHttpRequest(ctx, (FullHttpRequest) msg);
    } else if (msg instanceof WebSocketFrame) {
      handleWebSocketFrame(ctx, (WebSocketFrame) msg);
    }
  }


  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush();
  }

  private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {

    // Handle a bad request.
    if (!req.decoderResult().isSuccess()) {
      sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
      return;
    }

    // Allow only GET methods.
    if (req.method() != GET) {
      sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, FORBIDDEN));
      return;
    }

    QueryStringDecoder queryStringDecoder = new QueryStringDecoder(req.uri());
    Map<String, List<String>> parameters = queryStringDecoder.parameters();

    if (parameters.size() == 0 || !parameters.containsKey(HTTP_REQUEST_TOKEN)) {
      log.warn("'{}' query string is missing", HTTP_REQUEST_TOKEN);
      sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND));
      return;
    }

    try {
      client = RequestService.clientRegister(parameters.get(HTTP_REQUEST_TOKEN).get(0));
    } catch (JacksonException | IllegalArgumentException ex) {
      log.warn("Failed to register current client request", ex);
      sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
      return;
    }

    // Handshake
    WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(getWebSocketLocation(req), null, true);
    handshaker = wsFactory.newHandshaker(req);
    if (handshaker == null) {
      WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
    } else {
      ChannelFuture channelFuture = handshaker.handshake(ctx.channel(), req);

      // process channel
      if (channelFuture.isSuccess()) {
        log.info("Client '{}' with token '{}' connected successfully", client.getClientId(), client.getToken());

        // Add current channel
        client.setChannel(ctx.channel());
        WebSocketUtil.registerClient(client);
        log.debug("a new client joined from [{}], {}", ctx.channel().remoteAddress(), WebSocketUtil.summary());

        // response welcome message immediately
        Response welcomeResponse = Response.builder().data("welcome").build();
        ctx.channel().write(new TextWebSocketFrame(JacksonHelper.serialize(welcomeResponse)));

      }

    }

  }

  private void processTextFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
    if (client.getChannel() == null) {
      Response.ResponseBuilder responseBuilder = Response.builder()
        .clientId(client.getClientId())
        .topic(client.getTopic());
      responseBuilder.code(101).error("No connection");
      ctx.channel().writeAndFlush(new TextWebSocketFrame(JacksonHelper.serialize(responseBuilder.build())));
      return;
    }

    String requestMessage = ((TextWebSocketFrame) frame).text();
    processRequest(ctx, requestMessage);

  }

  private void processRequest(ChannelHandlerContext ctx, String requestMessage) {
    Response.ResponseBuilder responseBuilder = Response.builder()
      .clientId(client.getClientId())
      .topic(client.getTopic());
    try {
      Request request = JacksonHelper.deserialize(requestMessage, Request.class);

      log.debug("Received a command for '{}'", request.getCommand());
      WebSocketInboundProcessor processor = processorList.stream()
        .filter(x -> x.isSupportRequest(request))
        .findFirst()
        .orElse(null);

      if (processor != null) {

        try {
          if (processor instanceof WebSocketInboundFutureProcessor) {
            ((WebSocketInboundFutureProcessor) processor).futureProcess(client, request);
            return;
          } else {
            responseBuilder.data(processor.processRequest(request));
            responseBuilder.code(200);
            WebSocketUtil.broadcast(client, JacksonHelper.serialize(responseBuilder.build()));
          }
          return;
        } catch (RuntimeException ex) {
          responseBuilder.code(400);
          responseBuilder.error(ex.getMessage());
          log.warn("Failed to process request message", ex);
        }

      } else {
        responseBuilder.code(404).error("Unknown command '" + request.getCommand() + "'");
        log.warn("Cannot find a processor for command '{}'", request.getCommand());
      }

    } catch (JacksonException ex) {
      responseBuilder.code(400).error("Doesn't support incoming stream data");
      log.warn("Cannot deserialize websocket request properly", ex);
    }

    Response response = responseBuilder.build();
    log.debug("Returning response status '{}'", response.getCode());
    ctx.channel().write(new TextWebSocketFrame(JacksonHelper.serialize(response)));
  }

  private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

    if (frame instanceof CloseWebSocketFrame) {
      handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
      return;
    }
    if (frame instanceof PingWebSocketFrame) {
      ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
      return;
    }
    if (frame instanceof TextWebSocketFrame) {
      processTextFrame(ctx, frame);
      return;
    }

    if (frame instanceof BinaryWebSocketFrame) {
      byte[] data = new byte[frame.content().readableBytes()];
      ByteBuf byteBuf = frame.content();
      byteBuf.readBytes(data);
      String request = new String(data);
      log.debug("Received binary request via web socket : {}", request);
      processRequest(ctx, request);
      return;
    }

    throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));

  }

  private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
    if (res.status().code() != 200) {
      ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
      res.content().writeBytes(buf);
      buf.release();
      HttpUtil.setContentLength(res, res.content().readableBytes());
    }

    ChannelFuture f = ctx.channel().writeAndFlush(res);
    if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
      f.addListener(ChannelFutureListener.CLOSE);
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) {
    if (client != null) {
      WebSocketUtil.deregisterClient(client);
      client.setChannel(null);
      log.debug("a client left {}", WebSocketUtil.summary());
    }
  }

  private static String getWebSocketLocation(FullHttpRequest req) {
    String location = req.headers().get(HOST) + WEBSOCKET_BASE_PATH;
    return "ws://" + location;
  }

  private boolean isNotWebSocketPath(FullHttpRequest req) {
    try {
      return req.method() != GET || !WEBSOCKET_BASE_PATH.equals(new URI(req.uri()).getPath());
    } catch (URISyntaxException ex) {
      return false;
    }
  }

  private boolean isIgnoreRequest(FullHttpRequest req) {
    return req.method() == GET && Arrays.asList("/favicon.ico", "/").contains(req.uri());
  }

}
