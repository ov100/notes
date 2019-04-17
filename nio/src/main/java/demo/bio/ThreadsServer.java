package demo.bio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import demo.util.ReadInput;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 多线程，2个阻塞点
 *
 * @author : gb 2019/4/16
 */
public class ThreadsServer {

    void process() throws IOException {
        try (ServerSocket server = new ServerSocket(9091)) {

            long start = System.currentTimeMillis();
            while (true) {
                //阻塞点
                Socket socket = server.accept();
                getExecutor().execute(() -> {
                    try {
                        InputStream in = socket.getInputStream();
                        ReadInput.read(in);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                if (isEnd(start)) {
                    break;
                }
            }
        }
    }

    private boolean isEnd(long start) {
        long end = System.currentTimeMillis();
        return (end - start) > Integer.MAX_VALUE;
    }

    private ExecutorService getExecutor() {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        return new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    public static void main(String[] args) throws Exception {
        new ThreadsServer().process();
    }
}
