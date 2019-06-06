package im.zhaojun.common.shiro.realm;

import cn.hutool.core.lang.UUID;
import im.zhaojun.common.constants.AuthcTypeEnum;
import im.zhaojun.common.shiro.OAuth2Helper;
import im.zhaojun.common.shiro.token.OAuth2Token;
import im.zhaojun.common.util.ShiroUtil;
import im.zhaojun.common.util.WebHelper;
import im.zhaojun.system.model.User;
import im.zhaojun.system.model.UserAuths;
import im.zhaojun.system.service.UserAuthsService;
import im.zhaojun.system.service.UserService;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.ResponseStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * OAuth2 通用 Realm.
 */
public abstract class OAuth2Realm extends AuthenticatingRealm {

    private static final Logger log = LoggerFactory.getLogger(OAuth2Realm.class);

    @Resource
    private OAuth2Helper oAuth2Helper;

    @Resource
    private UserService userService;

    @Resource
    private UserAuthsService userAuthsService;

    /**
     * 授权类型, 需子类实现来表示是什么认证类型.
     */
    public abstract AuthcTypeEnum getAuthcTypeEnum();

    /**
     * 调用方法： {@link #getAuthcTypeEnum} 获取认证类型.
     * 用来判断该 Realm 是否用来处理此认证类型.
     * 并获取该类型对应的 clientId 和 clientSecret 和 redirectUrl.
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        if (token instanceof OAuth2Token) {
            OAuth2Token oAuth2Token = (OAuth2Token) token;
            AuthcTypeEnum authcTypeEnum = oAuth2Token.getAuthcTypeEnum();
            return authcTypeEnum.equals(getAuthcTypeEnum());
        }

        return false;
    }

    /**
     * 根据 token 获取用户信息.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        OAuth2Token oAuth2Token = (OAuth2Token) token;

        // 获取 code.
        String code = oAuth2Token.getAuthCode();

        // 根据 code 去 OAuth2 服务商获取用户信息.
        AuthUser oauthUser = extractUserInfo(code);

        // 获取该账号与当前系统的绑定关系.
        UserAuths userAuths;

        Subject subject = SecurityUtils.getSubject();

        boolean isAuthenticated = subject.isAuthenticated();

        if (isAuthenticated) {
            userAuths = userAuthsService.selectOneByIdentityTypeAndUserId(getAuthcTypeEnum(), ShiroUtil.getCurrentUser().getUserId());
        } else {
            userAuths = userAuthsService.selectOneByIdentityTypeAndIdentifier(getAuthcTypeEnum(), oauthUser.getUsername());
        }

        // 如果未绑定.
        if (userAuths == null) {

            Integer userId;

            // 如果未登录则创建一个用于与之关联.
            if (!subject.isAuthenticated()) {
                // 创建用户  (这里没有处理用户名重复的问题. 请自行根据业务处理.)
                User user = new User();
                Integer[] initRoleIds = {2};
                user.setUsername(oauthUser.getUsername());

                // oauth2 登录的用户, 给予一个随机的密码.
                String password = UUID.fastUUID().toString();
                String salt = String.valueOf(System.currentTimeMillis());
                String encryptPassword = new Md5Hash(password, salt).toString();
                user.setPassword(encryptPassword);
                user.setSalt(salt);
                user.setEmail(oauthUser.getEmail());
                user.setStatus("1");
                userService.add(user, initRoleIds);

                userId = user.getUserId();
            } else {
                userId = ((User)subject.getPrincipal()).getUserId();
            }
            // 绑定用户关系.
            userAuths = new UserAuths();
            userAuths.setUserId(userId);
            userAuths.setIdentifier(oauthUser.getUsername());
            userAuths.setIdentityType(getAuthcTypeEnum().getDescription());

            // 这里存起来 assessToken 用于后续再次调用服务提供商的接口获取相关信息. 虽然此系统后面没用到该参数.
            userAuths.setCredential(oauthUser.getToken().getAccessToken());
            userAuthsService.insert(userAuths);
        }

        User user = userService.selectOne(userAuths.getUserId());
        log.info(user.toString());
        return new SimpleAuthenticationInfo(user, code, getName());
    }

    // 获取用户信息
    private AuthUser extractUserInfo(String code) {
        AuthRequest authRequest = oAuth2Helper.getAuthRequest(getAuthcTypeEnum());
        AuthResponse authResponse = authRequest.login(code);

        // 如果认证失败. 则输出日志, 并将用户重定向到错误页面.
        // 这里出错一般原因为程序的 OAuth2 ClientID 或 clientSecret 或 redirectUrl 配置错误.
        if (authResponse.getCode() == ResponseStatus.FAILURE.getCode()) {
            log.error(authResponse.getMsg());
            WebHelper.redirectUrl("/oauth2/error");
        }

        return (AuthUser) authResponse.getData();
    }
}