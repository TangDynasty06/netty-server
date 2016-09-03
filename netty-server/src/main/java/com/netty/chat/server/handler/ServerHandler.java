package com.netty.chat.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.GenericFutureListener;

public class ServerHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		/*System.err.println("channel active,channelId:" + ctx.channel().id() + ",time:" + System.currentTimeMillis());
		ByteBuf buff = ctx.alloc().buffer(2);
		buff.writeChar(2);
		ChannelFuture future = ctx.writeAndFlush(buff.duplicate().retain());
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("server send over!");
			}
		});*/
		
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		
		System.err.println((String)msg);
		ctx.writeAndFlush("hi client,I'm server,I get Msg!");
		
		
		
		
		/*ByteBuf request = (ByteBuf)msg;
		byte[] requestArr = new byte[request.readableBytes()];
		request.readBytes(requestArr);
		String requestStr = new String(requestArr);
		System.err.println(requestStr);
		request.release();
		
		
		
		String responseStr = "hello client, I am ok!";
		ByteBuf response = ctx.alloc().buffer(4 * responseStr.length());
		response.writeBytes(responseStr.getBytes());
		ctx.write(response);
		ctx.flush();*/
		
		
		
		
		
		
		
		//System.err.println("channel msg read,channelId:" + ctx.channel().id());
		
		//super.channelRead(ctx, msg);
	}
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("channel register,channelId:" + ctx.channel().id() + ",time:" + System.currentTimeMillis());
		super.channelRegistered(ctx);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		/*System.err.println("channel read complete,channelId:" + ctx.channel().id());
		ctx.writeAndFlush("hello client");*/
		super.channelReadComplete(ctx);
	}
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("channel unregister,channelId:" + ctx.channel().id());
		super.channelUnregistered(ctx);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("channel excepetion,channelId:" + ctx.channel().id());
		super.exceptionCaught(ctx, cause);
	}
	
}
