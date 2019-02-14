package cn.com.jonpad.providesales;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class ProvideSalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvideSalesApplication.class, args);
    }

}

