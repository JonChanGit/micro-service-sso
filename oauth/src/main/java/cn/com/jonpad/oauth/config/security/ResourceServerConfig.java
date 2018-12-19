package cn.com.jonpad.oauth.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置，
 * @author Jon Chan
 * @date 2018/12/14 0:35
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
            csrf().disable()
            .authorizeRequests()
                .antMatchers("/v2/api-docs","/oauth/token", "/swagger-resources/**",  "/swagger-ui.html**", "/webjars/**", "favicon.ico")
                .permitAll()
            .and()
                .authorizeRequests().antMatchers("/**").authenticated();
            /*.and()
            .httpBasic();*/
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("sas")
                // 用于指示在这些资源上仅允许基于令牌的身份验证的标志。
                .stateless(true);
    }
}
