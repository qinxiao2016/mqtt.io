package com.mqtt.io.coder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import org.apache.log4j.Logger;

/**
 * 
 * @author yongboy
 * @time 2012-3-29
 * @version 1.0
 */
public class FlashSecurityHandler extends ChannelInboundHandlerAdapter {
	private final static Logger log = Logger
			.getLogger(FlashSecurityHandler.class);
	private static ByteBuf channelBuffer = Unpooled
			.copiedBuffer(
							"<cross-domain-policy>"
							+ "   <allow-access-from domain=\"*\" to-ports=\"*\" />"
							+ "</cross-domain-policy>", CharsetUtil.UTF_8);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object obj)
			throws Exception {
		ctx.writeAndFlush(channelBuffer).addListener(
				ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable e)
			throws Exception {
		log.warn("Exception now ...");
		ctx.close();
	}
}