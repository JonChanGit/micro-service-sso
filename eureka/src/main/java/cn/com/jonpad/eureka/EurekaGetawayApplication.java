package cn.com.jonpad.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaGetawayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaGetawayApplication.class, args);
    }
}
