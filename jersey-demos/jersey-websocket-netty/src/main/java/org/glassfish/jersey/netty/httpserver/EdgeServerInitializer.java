package org.glassfish.jersey.netty.httpserver;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;
import nz.co.asb.engagement.websocket.WebSocketServerHandler;

import java.net.URI;


public class EdgeServerInitializer extends JerseyServerInitializer {

  private final URI baseUri;
  private final SslContext sslCtx;
  private final EdgeNettyHttpContainer container;

  /**
   * Constructor for instantiating a new server initializer
   *
   * @param baseUri   base {@link URI} of the container (includes context path, if any).
   * @param sslCtx    SSL context.
   * @param container An extended Netty container implementation.
   */
  public EdgeServerInitializer(URI baseUri, SslContext sslCtx, EdgeNettyHttpContainer container) {
    super(baseUri, sslCtx, container, false);
    this.baseUri = baseUri;
    this.sslCtx = sslCtx;
    this.container = container;
  }

  /**
   * Override {@link JerseyServerInitializer#initChannel(SocketChannel)} to add additional
   * handlers to accept WebSocket connection.
   */
  @Override
  public void initChannel(SocketChannel ch) {
    ChannelPipeline p = ch.pipeline();

    if (sslCtx != null) {
      p.addLast(sslCtx.newHandler(ch.alloc()));
    }

    p.addLast(new HttpServerCodec());
    p.addLast(new ChunkedWriteHandler());
    p.addLast(new HttpObjectAggregator(65536));

    // Enable web socket channel handler
    p.addLast(new WebSocketServerCompressionHandler());
    p.addLast(new WebSocketServerHandler(container));

    // add HTTP channel handler
    p.addLast(new JerseyServerHandler(baseUri, container));
  }


}
