package top.cocoawork.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import top.cocoawork.chat.exception.NettyChatException;
import top.cocoawork.chat.server.handler.Byte2ChatMessageDecoder;
import top.cocoawork.chat.server.handler.ServerChatMessageReadHandler;

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
                        pipeline.addLast(new Byte2ChatMessageDecoder());
                        pipeline.addLast(new ServerChatMessageReadHandler());
                    }
                });

    }


    public ChannelFuture run() throws InterruptedException {
        return serverBootstrap.bind(port).sync()
                .channel().closeFuture().sync();
    }

    public void shutdown() {
        serverBootstrap.childGroup().shutdownGracefully();
        serverBootstrap.group().shutdownGracefully();
    }


    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer(9999);
        try {
            chatServer.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
