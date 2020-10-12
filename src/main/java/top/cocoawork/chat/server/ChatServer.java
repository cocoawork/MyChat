package top.cocoawork.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.CompleteFuture;
import io.netty.util.concurrent.GenericFutureListener;
import top.cocoawork.chat.codec.MyMessageToByteEncoder;
import top.cocoawork.chat.exception.NettyChatException;
import top.cocoawork.chat.codec.MyByteToMessageDecoder;
import top.cocoawork.chat.server.handler.MyServerMessageHandler;

import java.util.concurrent.CompletableFuture;

public class ChatServer {

    private Integer port;
    private ServerBootstrap serverBootstrap;

    public ChatServer(Integer port) {
        if (null == port) {
            throw new NettyChatException("服务端口为空");
        }
        this.port = port;
        this.serverBootstrap = setup();
    }


    private ServerBootstrap setup() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        return new ServerBootstrap().group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();

                        //添加日志handler
                        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
                        pipeline.addLast(loggingHandler);
                        //处理入站消息的处理器
                        pipeline.addLast(new MyByteToMessageDecoder());
                        //处理出站消息的处理器
                        pipeline.addLast(new MyMessageToByteEncoder());
                        //业务处理器
                        pipeline.addLast(new MyServerMessageHandler());

                    }
                });

    }


    public void run() throws InterruptedException {
        serverBootstrap.bind(port).sync()
                .channel().closeFuture().sync();
    }

    public void runWithListener(GenericFutureListener listener) throws InterruptedException {
        serverBootstrap.bind(port).sync().addListener(listener)
                .channel().closeFuture().sync();
    }


    public void shutdown() {
        serverBootstrap.childGroup().shutdownGracefully();
        serverBootstrap.group().shutdownGracefully();
    }


    public static void main(String[] args) throws InterruptedException {
        new ChatServer(8888).run();
    }

}
