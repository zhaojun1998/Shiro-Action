package im.zhaojun.common.shiro;

import im.zhaojun.common.constants.AuthcTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "shiro-action")
public class ShiroActionProperties {

    private String superAdminUsername;
    private Integer retryCount;
    private Boolean loginVerify;
    private Integer retryTimeout;
    private Integer sessionTimeout;
    private Integer permsCacheTimeout;

    public String getSuperAdminUsername() {
        return superAdminUsername;
    }

    public void setSuperAdminUsername(String superAdminUsername) {
        this.superAdminUsername = superAdminUsername;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getRetryTimeout() {
        return retryTimeout;
    }

    public void setRetryTimeout(Integer retryTimeout) {
        this.retryTimeout = retryTimeout;
    }

    public Boolean getLoginVerify() {
        return loginVerify;
    }

    public void setLoginVerify(Boolean loginVerify) {
        this.loginVerify = loginVerify;
    }

    private Map<AuthcTypeEnum, Provider> oauth2Provider = new HashMap<>();

    public Map<AuthcTypeEnum, Provider> getOauth2Provider() {
        return oauth2Provider;
    }

    public void setOauth2Provider(Map<AuthcTypeEnum, Provider> oauth2Provider) {
        this.oauth2Provider = oauth2Provider;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Integer getPermsCacheTimeout() {
        return permsCacheTimeout;
    }

    public void setPermsCacheTimeout(Integer permsCacheTimeout) {
        this.permsCacheTimeout = permsCacheTimeout;
    }

    @PostConstruct
    public void validate() {
        Set<String> set = new HashSet<>();

        for (Provider provider : this.oauth2Provider.values()) {
            // ClientId 不能为空
            if (!StringUtils.hasText(provider.getClientId())) {
                throw new IllegalStateException("Client id must not be empty.");
            }

            // ClientSecret 不能为空
            if (!StringUtils.hasText(provider.getClientSecret())) {
                throw new IllegalStateException("Client secret must not be empty.");
            }

            // 回调地址不能重复.
            if (!set.add(provider.redirectUrl)) {
                throw new IllegalStateException("redirectUrl must not be duplicate.");
            }
        }
    }

    public static class Provider {
        private String clientId;
        private String redirectUrl;
        private String clientSecret;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }
}