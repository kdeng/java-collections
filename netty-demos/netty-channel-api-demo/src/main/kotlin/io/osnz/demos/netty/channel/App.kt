package io.osnz.demos.netty.channel

import io.osnz.demos.netty.channel.api.HomeResource
import io.osnz.demos.netty.channel.handler.NettyDemoHandler

import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider
import org.glassfish.jersey.server.ResourceConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI

class App {
  companion object {
    @JvmField
    val log: Logger = LoggerFactory.getLogger(this::class.simpleName)
    val APP_HOST = "http://localhost:9090/";
  }
}

fun main() {

  val baseUri: URI = URI.create(App.APP_HOST)

  val config: ResourceConfig = createResourceConfig()

  val server = NettyHttpContainerProvider.createHttp2Server(baseUri, config, null)

  server.pipeline().addLast(NettyDemoHandler())
  App.log.info("Server started -> {}", baseUri)
  Thread.currentThread().join()
}

fun createResourceConfig(): ResourceConfig {
  val resourceConfig = ResourceConfig()
//  resourceConfig.register(JerseyServerLogger::class.java)
  resourceConfig.register(HomeResource::class.java)
  return resourceConfig
}

