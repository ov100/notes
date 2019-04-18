package demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author : gb 2019/4/17
 */
public class MyClient {
    static final String HOST = System.getProperty("myapplication.ip");
    static final int PORT = 9093;

    private void run() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new MyClientInitializer(null));

            // Start the client.
            Channel ch = b.connect(HOST, PORT).sync().channel();

            ChannelFuture lastWriteFuture = null;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    String line = in.readLine();
                    if (line == null || "bye".equalsIgnoreCase(line)) {
                        ch.closeFuture().sync();
                        break;
                    }

                    lastWriteFuture = ch.writeAndFlush(line + "\r\n");
                }
            }


            // Wait until all messages are flushed before closing the channel.
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new MyClient().run();
    }
}
