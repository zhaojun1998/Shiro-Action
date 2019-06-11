package im.zhaojun.common.shiro.filter;

import im.zhaojun.common.constants.AuthcTypeEnum;
import im.zhaojun.common.exception.UnknownRedirectUrlException;
import im.zhaojun.common.shiro.OAuth2Helper;
import im.zhaojun.common.shiro.token.OAuth2Token;
import im.zhaojun.common.util.WebHelper;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * OAuth2 认证过滤器
 */
public class OAuth2AuthenticationFilter extends AuthenticatingFilter {

    private final OAuth2Helper oAuth2Helper;

    // oauth2 authc code 参数名
    private static final String AUTHC_CODE_PARAM = "code";

    public OAuth2AuthenticationFilter(OAuth2Helper oAuth2Helper) {
        this.oAuth2Helper = oAuth2Helper;
    }

    /**
     * 使用 OAuth2 服务提供商返回的 code 构建 token
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String code = httpRequest.getParameter(AUTHC_CODE_PARAM);

        // 根据回调地址, 判断当前的认证类型是什么
        String requestURI = httpRequest.getRequestURI();
        AuthcTypeEnum authcType = oAuth2Helper.getAuthcTypeByRedirectUrl(requestURI);

        if (authcType == null) {
            throw new UnknownRedirectUrlException("未知的回调地址:" + requestURI);
        }

        // 创建 token 到 realm 中执行.
        return new OAuth2Token(code, authcType);
    }

    /**
     * 是否允许访问, 直接返回为不允许, 因为本页面只是为了进行认证, 并跳转到首页.
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    /**
     * 当 isAccessAllowed 不允许访问时, 判断 oauth2 服务提供商是否返回了错误信息 <p>
 *     如果没有返回错误信息, 则判断
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String error = request.getParameter("error");
        String errorDescription = request.getParameter("error_description");

        if (!StringUtils.isEmpty(error)) { // 如果服务端返回了错误
            WebUtils.issueRedirect(request, response, "/error?error=" + error + "error_description=" + errorDescription);
            return false;
        }

        if (StringUtils.isEmpty(request.getParameter(AUTHC_CODE_PARAM))) {
            // 如果用户没有身份验证, 且没有 auth code, 则重定向到登录页面.
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }

        // 执行登录操作.
        return executeLogin(request, response);
    }

    // 当登录成功, 跳转到  shiro 配置的 successUrl.
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) {
        WebHelper.redirectUrl("/oauth2/success");
        return false;
    }

}