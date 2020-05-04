package io.osnz.demos.netty.channel.handler

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.charset.StandardCharsets


class NettyDemoHandler : ChannelDuplexHandler() {

  private val log: Logger = LoggerFactory.getLogger(this::class.simpleName)

  override fun channelReadComplete(ctx: ChannelHandlerContext) {
    log.info("channel read complete")
    ctx.flush()
  }

//  @Throws(Exception::class)
//  override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
//    log.info("channelRead")
////    ctx.writeAndFlush("Hello from channel handler") // I have also tried writeAndFlush(msg)
//    super.channelRead(ctx, msg)
//  }

  @Throws(Exception::class)
  override fun write(ctx: ChannelHandlerContext, msg: Any, promise: ChannelPromise) {
    log.info("write")
    super.write(ctx, msg, promise)
  }

  @Throws(Exception::class)
  override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {

    val buf: ByteBuf = msg as ByteBuf

    val req: ByteArray = ByteArray(buf.readableBytes())
    buf.readBytes(req)
    val body: String = String(req, StandardCharsets.UTF_8)
    log.debug("Received body : $body")
    println("Received body : $body")
  }


  override fun handlerAdded(ctx: ChannelHandlerContext) {
    log.debug("Handler added")
  }

  override fun channelActive(ctx: ChannelHandlerContext?) {
    log.debug("channel active")
    super.channelActive(ctx)
  }

}
