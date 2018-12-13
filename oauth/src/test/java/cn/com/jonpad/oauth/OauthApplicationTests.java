package cn.com.jonpad.oauth;

import cn.com.jonpad.oauth.entity.SysUser;
import cn.com.jonpad.oauth.mapper.SysUserMapper;
import cn.com.jonpad.oauth.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OauthApplicationTests {

    @Autowired
    UserDetailsService service;

    @Test
    public void contextLoads() {
        System.out.println(service.loadUserByUsername("super"));
    }

}
