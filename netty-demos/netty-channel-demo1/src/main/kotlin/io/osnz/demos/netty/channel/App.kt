package io.osnz.demos.netty.channel

import cd.connect.lifecycle.ApplicationLifecycleManager
import cd.connect.lifecycle.LifecycleStatus
import io.osnz.demos.netty.channel.api.HomeResource
import io.osnz.demos.netty.channel.handler.NettyDemoHandler
import io.osnz.demos.netty.channel.log.logger
import org.glassfish.jersey.logging.JerseyServerLogger
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider
import org.glassfish.jersey.server.ResourceConfig
import org.slf4j.Logger
import java.net.URI

class App {
  companion object {
    @JvmField
    val log: Logger = logger(this)
  }
}

fun main() {
  ApplicationLifecycleManager.updateStatus(LifecycleStatus.STARTING)

  val baseUri: URI = URI.create("http://localhost:9090")

  val config: ResourceConfig = createResourceConfig()

  val server = NettyHttpContainerProvider.createHttp2Server(baseUri, config, null)
  server.pipeline().addFirst(NettyDemoHandler())

  ApplicationLifecycleManager.registerListener { trans ->
    if (trans.next == LifecycleStatus.TERMINATING) {
      server.close()
    }
  }

  App.log.info("Engagement Edge Platform started -> {}", baseUri)
  ApplicationLifecycleManager.updateStatus(LifecycleStatus.STARTED)
  Thread.currentThread().join()
}

fun createResourceConfig(): ResourceConfig {
  val resourceConfig = ResourceConfig()
  resourceConfig.register(JerseyServerLogger::class.java)
  resourceConfig.register(HomeResource::class.java)
  return resourceConfig
}

