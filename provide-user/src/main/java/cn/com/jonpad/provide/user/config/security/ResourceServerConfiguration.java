package cn.com.jonpad.provide.user.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author Jon Chan
 * @date 2019/1/16 23:57
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;

    /**
     * 注入feign请求拦截器，将token传递到feign调用的服务
     * @return
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(){
        return requestTemplate -> {
            log.error("xxxx");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            requestTemplate.header("Authorization", "bearer " + details.getTokenValue());
        };
    }
     */

    /**
     * 配置资源的保护
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/v2/api-docs", "/swagger-resources/**",  "/swagger-ui.html**", "/webjars/**", "favicon.ico").permitAll()
                .and().authorizeRequests().antMatchers("/actuator/**").hasAuthority("SUPPER").antMatchers("/**").authenticated();
    }

    /**
     * 配置资源服务器安全属性，如Token的配置，这些是与 AuthorizationServer 授权服务器的配置是匹配的。
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("provide-user").tokenStore(tokenStore);
    }
}
