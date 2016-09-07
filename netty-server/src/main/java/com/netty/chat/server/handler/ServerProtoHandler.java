package com.netty.chat.server.handler;

import com.chat.common.message.QchatMessage;
import com.chat.common.message.QchatMessage.person1;
import com.google.protobuf.MessageLite;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerProtoHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// TODO Auto-generated method stub
		if(msg instanceof MessageLite){
//			QchatMessage.person1 p = (QchatMessage.person1)msg;
//			System.err.println(p.getId() + " , " + p.getName());
//			
//			person1.Builder builder = person1.newBuilder();
//			builder.setId(102);
//			builder.setName("sever");
//			ctx.writeAndFlush(builder.build());
			
			MessageLite re = (MessageLite)msg;
			System.err.println(re + ",server println");
			ctx.writeAndFlush(re);
			
		}
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
}
