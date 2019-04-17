package demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author : gb 2019/4/17
 */
public class Server {

    void process() throws IOException {
        try (ServerSocketChannel channel = ServerSocketChannel.open()) {
            channel.socket().bind(new InetSocketAddress(9092));


            while (true){
                SocketChannel socket = channel.accept();
            }
        }
    }
}
