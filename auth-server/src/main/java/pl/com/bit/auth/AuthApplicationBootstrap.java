package pl.com.bit.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Nullable;

@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class AuthApplicationBootstrap {

    public static void main(@Nullable String[] args) {
        SpringApplication.run(AuthApplicationBootstrap.class, args);
    }
}
