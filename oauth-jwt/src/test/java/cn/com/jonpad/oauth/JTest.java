package cn.com.jonpad.oauth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Jon Chan
 * @date 2018/12/19 17:02
 */
public class JTest {
    @Test
    public void test01()throws Exception{
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.printf(passwordEncoder.encode("app"));
    }
}
