package demo.bio;

import demo.util.ReadInput;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程，2个阻塞点
 *
 * @author : gb 2019/4/16
 */
public class Server {
    void process() throws IOException {
        try (ServerSocket server = new ServerSocket(9090)) {
            //阻塞点
            Socket socket = server.accept();

            InputStream in = socket.getInputStream();

            ReadInput.read(in);
        }
    }

    public static void main(String[] args) throws Exception {
        new Server().process();
    }
}
