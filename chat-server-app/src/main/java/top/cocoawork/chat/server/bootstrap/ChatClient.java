package top.cocoawork.chat.server.bootstrap;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.GenericFutureListener;
import top.cocoawork.chat.server.handler.MyByteToMessageDecoder;
import top.cocoawork.chat.server.handler.MyClientMessageHandler;
import top.cocoawork.chat.server.handler.MyMessageToByteEncoder;
import top.cocoawork.chat.server.message.ChatMessage;



public class ChatClient {

    private String host;
    private Integer port;

    private MyClientMessageHandler messageHandler = new MyClientMessageHandler();

    private Bootstrap bootstrap;

    public ChatClient(String host, Integer port) {
        this.host = host;
        this.port = port;
        this.bootstrap = setup();
    }

    private Bootstrap setup() {
        EventLoopGroup group = new NioEventLoopGroup();
        return new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();

                        //添加日志handler
                        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
                        pipeline.addLast(loggingHandler);

                        //处理出站消息的处理器
                        pipeline.addLast(new MyMessageToByteEncoder());
                        //处理入站消息的处理器
                        pipeline.addLast(new MyByteToMessageDecoder());
                        //业务处理器
                        pipeline.addLast(messageHandler);

                    }
                });
    }


    public void run() throws InterruptedException {
        bootstrap.connect(host,port).sync()
                .channel().closeFuture().sync();
    }

    public void runWithListener(GenericFutureListener listener) throws InterruptedException {
        bootstrap.connect(host,port).addListener(listener);
    }


    public void shutdown() {
        bootstrap.group().shutdownGracefully();
    }


    public void sendMessage(ChatMessage message) {
        messageHandler.sendMessage(message);
    }


}
