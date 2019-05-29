package im.zhaojun.common.shiro.realm;

import im.zhaojun.common.constants.AuthcTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class OAuth2GithubRealm extends OAuth2Realm {

    @Override
    public AuthcTypeEnum getAuthcTypeEnum() {
        return AuthcTypeEnum.GITHUB;
    }
}
