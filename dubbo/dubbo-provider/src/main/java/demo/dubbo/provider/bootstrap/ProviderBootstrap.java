package demo.dubbo.provider.bootstrap;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author : gb 2019/4/18
 */
@EnableAutoConfiguration
public class ProviderBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ProviderBootstrap.class).run(args);
    }
}
