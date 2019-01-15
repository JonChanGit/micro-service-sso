package cn.com.jonpad.oauth.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * @author Jon Chan
 * @date 2018/12/19 15:29
 */
@Slf4j
public class JWTAuthorizationExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        log.error("SsoWebResponseExceptionTranslator ===>>",e);
        return null;
    }
}
