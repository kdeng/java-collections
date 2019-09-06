package io.osnz.demos.netty.channel.handler

import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise

import org.slf4j.Logger
import org.slf4j.LoggerFactory


class NettyDemoHandler : ChannelDuplexHandler() {

  private val log: Logger = LoggerFactory.getLogger(this::class.simpleName)

  override fun channelReadComplete(ctx: ChannelHandlerContext) {
    ctx.flush()
  }

  @Throws(Exception::class)
  override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
    ctx.writeAndFlush("Hello world") // I have also tried writeAndFlush(msg)
  }

  @Throws(Exception::class)
  override fun write(ctx: ChannelHandlerContext, msg: Any, promise: ChannelPromise) {
    ctx.write(msg, promise)
  }

//  override fun channelRead(ctx: ChannelHandlerContext, msg: Object) {
//    val buf: ByteBuf = msg as ByteBuf
//    val req: ByteArray = ByteArray(buf.readableBytes())
//    buf.readBytes(req)
//    val body: String = String(req, StandardCharsets.UTF_8)
//    log.debug("Received body : $body")
//    println("Received body : $body")
//  }
//
//
//  override fun handlerAdded(ctx: ChannelHandlerContext): Void {
//    log.debug("Hadnler added")
//  }

}
