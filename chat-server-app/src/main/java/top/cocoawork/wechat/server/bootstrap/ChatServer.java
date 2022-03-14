package top.cocoawork.wechat.server.bootstrap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import top.cocoawork.wechat.server.exception.ChatServerException;
import top.cocoawork.wechat.server.handler.MyByteToMessageDecoder;
import top.cocoawork.wechat.server.handler.MyMessageToByteEncoder;
import top.cocoawork.wechat.server.handler.MyServerMessageHandler;

public class ChatServer {

    private final Integer port;
    private final ServerBootstrap bootstrap;

    public ChatServer(Integer port) {
        if (null == port) {
            throw new ChatServerException("服务端口未指定！");
        }
        this.port = port;
        this.bootstrap = setupBootstrap();
    }

    private final ServerBootstrap setupBootstrap() {
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

                        //添加心跳检测
//                        pipeline.addLast(new IdleStateHandler(2,2,3, TimeUnit.SECONDS));

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
        bootstrap.bind(port).sync()
                .channel().closeFuture().sync();
    }

}
