package demo.bio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

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
public class MyThreadsBioServer {

    public void process() throws IOException {
        try (ServerSocket server = new ServerSocket(9091)) {

            long start = System.currentTimeMillis();
            while (true) {
                //阻塞点
                Socket socket = server.accept();
                new Thread(() -> {
                    try {
                        run(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();

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

    private void run(Socket socket) throws Exception {
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

    private ExecutorService getExecutor(){

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        return new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

//        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
//        singleThreadPool.shutdown();
    }

    public static void main(String[] args) throws Exception {
        new MyThreadsBioServer().process();
    }
}
