package cn.com.jonpad.oauth.boot;

import cn.com.jonpad.oauth.entity.SysUser;
import cn.com.jonpad.oauth.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 初始化数据
 * @author Jon Chan
 * @date 2018/12/19 14:20
 */
@Slf4j
@Component
@Order(1)
public class Starter implements CommandLineRunner {

    @Autowired
    SysUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${supper_user_password:12345678}")
    String SUPPER_USER_PASSWORD;

    @Override
    public void run(String... args) throws Exception {
        SysUser admin = new SysUser();
        admin.setId(1L);
        admin.setUsername("super");
        admin.setPassword(passwordEncoder.encode(SUPPER_USER_PASSWORD));
        admin.setName("Supper Admin");
        userService.saveOrUpdate(admin);
    }
}
