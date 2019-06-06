package im.zhaojun.common.shiro;

import im.zhaojun.common.constants.AuthcTypeEnum;
import im.zhaojun.common.exception.AuthcTypeNotSupportException;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.BaseAuthRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * OAuth2 工具类
 */
@Component
public class OAuth2Helper {

    @Resource
    private ShiroActionProperties properties;

    /**
     * 获取所有 OAuth2 配置对象
     */
    private Map<AuthcTypeEnum, ShiroActionProperties.Provider> getAllProvider() {
        return properties.getOauth2Provider();
    }

    /**
     * 根据类型获取单个 OAuth2 配置对象.
     */
    public ShiroActionProperties.Provider getProvider(AuthcTypeEnum identifyType) {
        return getAllProvider().get(identifyType);
    }

    /**
     * 根据类型获取 AuthRequest 对象.
     */
    public AuthRequest getAuthRequest(AuthcTypeEnum authcTypeEnum) {
        ShiroActionProperties.Provider provider = getProvider(authcTypeEnum);
        if (provider == null) {
            throw new AuthcTypeNotSupportException("系统暂未开启 " + authcTypeEnum.getDescription() + " 登录");
        }

        BaseAuthRequest authRequest = null;

        AuthConfig authConfig = AuthConfig.builder()
                .clientId(provider.getClientId())
                .clientSecret(provider.getClientSecret())
                .redirectUri(provider.getRedirectUrl())
                .build();

        switch (authcTypeEnum) {
            case GITHUB:
                authRequest = new AuthGithubRequest(authConfig);
                break;
            case GITEE:
                authRequest = new AuthGiteeRequest(authConfig);
                break;
        }
        return authRequest;
    }

    /**
     * 根据回调地址获取当前认证的类型.
     * @param requestURI    回调地址. (尾匹配法)
     */
    public AuthcTypeEnum getAuthcTypeByRedirectUrl(String requestURI) {
        for (AuthcTypeEnum key : getAllProvider().keySet()) {
            String redirectUrl = getProvider(key).getRedirectUrl();
            if (redirectUrl.endsWith(requestURI)) {
                return key;
            }
        }

        return null;
    }
}