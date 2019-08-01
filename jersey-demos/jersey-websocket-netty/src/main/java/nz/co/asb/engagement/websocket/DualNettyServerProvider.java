package nz.co.asb.engagement.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.glassfish.jersey.netty.httpserver.EdgeNettyHttpContainer;
import org.glassfish.jersey.netty.httpserver.EdgeServerInitializer;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ProcessingException;
import java.net.URI;

/**
 * Edge Server Provider helps to create a server for consuming both
 * web socket and HTTP requests.
 */
public class DualNettyServerProvider {

  /**
   * Create and start Netty server.
   *
   * @param baseUri       base uri.
   * @param configuration Jersey configuration.
   * @param sslContext    Netty SSL context (can be null).
   *
   * @return Netty channel instance.
   *
   * @throws ProcessingException when there is an issue with creating new container.
   */
  public static Channel createServer(final URI baseUri, final ResourceConfig configuration,
                                     SslContext sslContext) throws ProcessingException {

    // Configure the server.
    final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    final EventLoopGroup workerGroup = new NioEventLoopGroup();
    final EdgeNettyHttpContainer container = new EdgeNettyHttpContainer(configuration);

    try {
      ServerBootstrap b = new ServerBootstrap();
      b.option(ChannelOption.SO_BACKLOG, 1024);
      b.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(new EdgeServerInitializer(baseUri, sslContext, container));

      int port = getPort(baseUri);

      Channel ch = b.bind(port).sync().channel();

      ch.closeFuture().addListener(new GenericFutureListener<Future<? super Void>>() {
        @Override
        public void operationComplete(Future<? super Void> future) throws Exception {
          container.getApplicationHandler().onShutdown(container);

          bossGroup.shutdownGracefully();
          workerGroup.shutdownGracefully();
        }
      });

      return ch;

    } catch (InterruptedException e) {
      throw new ProcessingException(e);
    }
  }


  private static int getPort(URI uri) {
    if (uri.getPort() == -1) {
      if ("http".equalsIgnoreCase(uri.getScheme())) {
        return 80;
      } else if ("https".equalsIgnoreCase(uri.getScheme())) {
        return 443;
      }

      throw new IllegalArgumentException("URI scheme must be 'http' or 'https'.");
    }

    return uri.getPort();
  }

}
