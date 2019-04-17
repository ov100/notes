package demo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程，2个阻塞点
 *
 * @author : gb 2019/4/16
 */
public class MyBioServer {
    public void process() throws IOException {
        try (ServerSocket server = new ServerSocket(9090)) {
            //阻塞点
            Socket socket = server.accept();

            InputStream in = socket.getInputStream();

            byte[] b = new byte[1024];
            while (true) {
                //阻塞点
                int length = in.read(b);
                if (length != -1) {
                    String data = new String(b, 0, length, "utf-8");
                    System.out.println(data);
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new MyBioServer().process();
    }
}
