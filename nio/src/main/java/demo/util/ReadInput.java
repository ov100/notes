package demo.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : gb 2019/4/17
 */
public class ReadInput {

    public static void read(InputStream in) throws IOException {
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
