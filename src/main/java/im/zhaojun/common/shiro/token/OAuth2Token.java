package im.zhaojun.common.shiro.token;

import im.zhaojun.common.constants.AuthcTypeEnum;
import org.apache.shiro.authc.AuthenticationToken;

public class OAuth2Token implements AuthenticationToken {

    private static final long serialVersionUID = 636072434492299237L;

    private String authCode;
    private String principal;
    private AuthcTypeEnum authcTypeEnum;

    public OAuth2Token(String authCode, AuthcTypeEnum authcTypeEnum) {
        this.authCode = authCode;
        this.authcTypeEnum = authcTypeEnum;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public AuthcTypeEnum getAuthcTypeEnum() {
        return authcTypeEnum;
    }

    public void setAuthcTypeEnum(AuthcTypeEnum authcTypeEnum) {
        this.authcTypeEnum = authcTypeEnum;
    }

    @Override
    public Object getCredentials() {
        return authCode;
    }
}