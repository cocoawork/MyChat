package top.cocoawork.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import top.cocoawork.chat.client.handler.ChatMessageToByteEncoder;
import top.cocoawork.chat.client.handler.ClientChatMessageSendHandler;

public class ChatClient {

    private String host;
    private Integer port;

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
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ChatMessageToByteEncoder());
                        pipeline.addLast(new ClientChatMessageSendHandler());
                    }
                });
    }


    public void run() throws InterruptedException {
        bootstrap.connect(host,port).sync()
                .channel().closeFuture().sync();
    }

    public void runWithListener(GenericFutureListener listener) throws InterruptedException {
        bootstrap.connect(host,port).addListener(listener).sync()
                .channel().closeFuture().sync();
    }


    public void shutdown() {
        bootstrap.group().shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        new ChatClient("127.0.0.1", 8989).run();
    }


}
