package demo.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : gb 2019/4/20
 */
@SpringBootApplication
public class ConsumerBootstrap {
    public static void main(String[] args) {
        new SpringApplication(ConsumerBootstrap.class).run(args);
    }
}
