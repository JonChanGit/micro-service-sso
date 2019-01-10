package cn.com.jonpad.oauth.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Jon Chan
 * @date 2019/1/9 23:57
 */
@Configuration
public class JWTTokenStoreConfiguration {

    @Value("${signing.key:123456}")
    private String signingKey;
    @Bean
    public TokenStore tokenStore(){
        //TokenStore实现，只从令牌本身读取数据。
        // 不是真正的商店，因为它永远不会持久存在，getAccessToken（OAuth2Authentication）等方法总是返回null。
        // 但它仍然是一个有用的工具，因为它将访问令牌转换为身份验证和来自身份验证。
        // 在需要TokenStore的任何地方使用它，但请记住使用与令牌铸造时使用的相同的JwtAccessTokenConverter实例（或具有相同验证程序的实例）。
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }
}
