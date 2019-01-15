package cn.com.jonpad.oauth.config.security;

import cn.com.jonpad.oauth.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * 授权服务配置
 *
 * @author Jon Chan
 * @date 2019/1/9 22:34
 */
@Configuration
@EnableAuthorizationServer
public class JWTAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 配置 AuthorizationServer 的安全属性，也就是endpoint /oauth/token 。/oauth/authorize 则和其它用户 REST 一样保护。可以不配置。
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
        security.passwordEncoder(passwordEncoder);
    }

    /**
     * 配置 ClientDetailsService 独立client客户端的信息。
     * 包括权限范围、授权方式、客户端权限等配置。
     * 授权方式有4种:implicit, client_redentials, password , authorization_code, 其中密码授权方式必须结合 AuthenticationManager 进行配置。
     * 必须至少配置一个客户端。
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory()
                // 客户端id
                .withClient("clientapp")
                // （对于可信任的客户端是必须的）客户端的私密信息。
                .secret(passwordEncoder.encode("12345678"))
                // 授权给客户端使用的权限类型。默认值为空。
                .authorizedGrantTypes("refresh_token", "password")
                .accessTokenValiditySeconds(1800)
                // 授权给客户端的权限（Spring普通的安全权限）。
                .scopes("base");
    }

    @Bean
    public TokenEnhancer jwtTokenEnhancer(){
        return new JWTTokenEnhancer();
    }

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator(){
        return new JWTAuthorizationExceptionTranslator();
    }

    /**
     * 配置AuthorizationServer 端点的非安全属性，
     * 也就是 token 存储方式、token 配置、用户授权模式等。
     * 默认不需做任何配置，除非使用 密码授权方式, 这时候必须配置 AuthenticationManager。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 一个复合令牌增强器，循环遍历其委托增强器。
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer(), jwtAccessTokenConverter));
        // Token存储通过配置 TokenStore，默认使用内存存储。AuthorizationServerEndpointsConfigurer 或 DefaultTokenServices 入口配置。配置方式有
        //
        //InMemoryTokenStore 默认方式，保存在本地内存
        //JdbcTokenStore 存储数据库
        //RedisTokenStore 存储Redis，这应该是微服务下比较常用方式
        //JwtTokenStore
        endpoints.tokenStore(tokenStore)
                // 访问令牌转换器
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain)
                //password模式下，验证resource owner
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .exceptionTranslator(webResponseExceptionTranslator());
        //Token 生命周期管理接口 AuthorizationServerTokenServices, 默认使用: DefaultTokenServices。
    }
}
