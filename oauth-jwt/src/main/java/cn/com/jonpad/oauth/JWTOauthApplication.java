package cn.com.jonpad.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.com.jonpad.oauth.mapper")
public class JWTOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(JWTOauthApplication.class, args);
    }
}
