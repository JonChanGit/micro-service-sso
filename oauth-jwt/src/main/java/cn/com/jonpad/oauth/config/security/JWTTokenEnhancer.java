package cn.com.jonpad.oauth.config.security;

import cn.com.jonpad.oauth.entity.SysUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jon Chan
 * @date 2019/1/9 23:35
 */
public class JWTTokenEnhancer implements TokenEnhancer {
    /**
     * TokenEnhancer提供在创建新令牌以供客户端使用的过程中自定义访问令牌的机会（例如，通过其附加信息映射）。
     * @param accessToken 当前访问令牌及其到期和刷新令牌
     * @param authentication 当前身份验证，包括客户端和用户详细信息
     * @return 增加了附加信息的新令牌
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Object principal = authentication.getPrincipal();
        SysUser userEntity = (SysUser) principal;
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("userId", userEntity.getId());
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
