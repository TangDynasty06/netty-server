package com.netty.chat.server;



import java.util.List;

import com.chat.common.message.QchatMessage;
import com.chat.common.netty.handler.decode.ByteToMessageDecode;
import com.chat.common.netty.handler.decode.LengthFieldBasedFrameDecoder;
import com.chat.common.netty.handler.decode.ProtobufDecoder;
import com.chat.common.netty.handler.decode.ProtobufVarint32FrameDecoder;
import com.chat.common.netty.handler.encode.MessageToByteEncode;
import com.chat.common.netty.handler.encode.MsgEncode;
import com.chat.common.netty.handler.encode.ProtobufEncoder;
import com.chat.common.netty.handler.encode.ProtobufVarint32LengthFieldPrepender;
import com.chat.common.scan.MsgScan;
import com.netty.chat.server.handler.ServerProtoHandler;
import com.netty.chat.server.handler.ServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class NettyServer {
	
	public void openPort(int port){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup)
			 .channel(NioServerSocketChannel.class)
			 .childHandler(new ChannelInitializer<SocketChannel>() {
	             @Override
	             public void initChannel(SocketChannel ch) throws Exception {
	            	 ChannelPipeline pipeline = ch.pipeline();
//					 字符串解码格式的
//	            	 ByteBuf delimiter = Unpooled.copiedBuffer("@$_F_F".getBytes());
//	            	 pipeline.addLast(
//	            			 			new DelimiterBasedFrameDecoder(1024, delimiter),
//	            			 			new StringDecoder(),
//	            			 			new MsgEncode(),
//	            			 			new ServerHandler()
//	            			 		);
	            	 
	            	 
	            	 

//				     proto尝试	            	 
//	            	 pipeline.addLast(
//	            			 			new ProtobufVarint32FrameDecoder(),
//	            			 			new ProtobufDecoder(QchatMessage.person1.getDefaultInstance()),
//	            			 			new ProtobufVarint32LengthFieldPrepender(),
//	            			 			new ProtobufEncoder(),
//	            			 			new ServerProtoHandler()
//	            			 		 );
	            	 
	            	 //自定义尝试
	            	 pipeline.addLast(
     			 			new ByteToMessageDecode(),
     			 			new MessageToByteEncode(),
     			 			new ServerProtoHandler()
     			 		 );
	            	 
	            	 
	            	 
	            	 
	             }
	         })
			 .option(ChannelOption.SO_BACKLOG, 128)
			 .childOption(ChannelOption.SO_KEEPALIVE, true);
			 ChannelFuture f = b.bind(port).sync();
			 f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		MsgScan msgScan = new MsgScan("com/chat/common/message");
		msgScan.initMsg();
		NettyServer server = new NettyServer();
		server.openPort(6100);
	}
}
