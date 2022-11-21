package org.adde0109.ambassador.forge;

import com.velocitypowered.proxy.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class FML2CRPMResetCompleteListener extends ChannelInboundHandlerAdapter {

  final Runnable whenComplete;

  public FML2CRPMResetCompleteListener(Runnable whenComplete) {
    this.whenComplete = whenComplete;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof ByteBuf buf) {
      if (!ctx.channel().isActive() || !buf.isReadable()) {
        buf.release();
        return;
      }

      int originalReaderIndex = buf.readerIndex();
      int packetId = ProtocolUtils.readVarInt(buf);
      if (packetId == 0x02 && buf.readableBytes() > 1) {
        ReferenceCountUtil.release(msg);
        ctx.pipeline().remove(this);
        whenComplete.run();
        return;
      }
    }
    ctx.fireChannelRead(msg);
  }
}