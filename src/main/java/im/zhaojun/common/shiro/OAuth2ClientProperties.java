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

/**
 * OAuth2 配置类
 */
@Component
@ConfigurationProperties(prefix = "shiro-action.oauth2", ignoreUnknownFields = false)
public class OAuth2ClientProperties {

    private Map<AuthcTypeEnum, Provider> provider = new HashMap<>();

    public Map<AuthcTypeEnum, Provider> getProvider() {
        return provider;
    }

    public void setProvider(Map<AuthcTypeEnum, Provider> provider) {
        this.provider = provider;
    }

    @PostConstruct
    public void validate() {
        Set<String> set = new HashSet<>();

        for (Provider provider : this.provider.values()) {
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
