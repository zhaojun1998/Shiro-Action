package im.zhaojun.common.shiro.filter;

import im.zhaojun.common.util.IPUtils;
import im.zhaojun.common.util.ResultBean;
import im.zhaojun.common.util.WebHelper;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 修改后的 perms 过滤器, 添加对 AJAX 请求的支持.
 */
public class RestAuthorizationFilter extends PermissionsAuthorizationFilter {

    private static final Logger log = LoggerFactory
            .getLogger(RestAuthorizationFilter.class);

    @Override
    protected boolean pathsMatch(String path, ServletRequest request) {
        boolean flag;
        String requestURI = this.getPathWithinApplication(request);

        String[] strings = path.split("==");

        if (strings.length <= 1) {
            // 普通的 URL, 正常处理
            flag =  this.pathsMatch(strings[0], requestURI);
        } else {
            // 获取当前请求的 http method.
            String httpMethod = WebUtils.toHttp(request).getMethod().toUpperCase();

            // 匹配当前请求的 http method 与 过滤器链中的的是否一致
            flag =  httpMethod.equals(strings[1].toUpperCase()) && this.pathsMatch(strings[0], requestURI);
        }

        if (flag) {
            log.debug("URL : [{}] matching perms filter : [{}]", requestURI, path);
        }
        return flag;
    }

    /**
     * 当没有权限被拦截时:
     *          如果是 AJAX 请求, 则返回 JSON 数据.
     *          如果是普通请求, 则跳转到配置 UnauthorizedUrl 页面.
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 如果未登录
        if (subject.getPrincipal() == null) {
            // AJAX 请求返回 JSON
            if (WebHelper.isAjaxRequest(WebUtils.toHttp(request))) {
                if (log.isDebugEnabled()) {
                    log.debug("sessionId: [{}], ip: [{}] 请求 restful url : {}, 未登录被拦截.",
                            httpServletRequest.getRequestedSessionId(),
                            IPUtils.getIpAddr(),
                            this.getPathWithinApplication(request));
                }
                WebHelper.writeJson(ResultBean.error("未登录"), response);
            } else {
                // 其他请求跳转到登陆页面
                saveRequestAndRedirectToLogin(request, response);
            }
        } else {
            // 如果已登陆, 但没有权限
            // 对于 AJAX 请求返回 JSON
            if (WebHelper.isAjaxRequest(WebUtils.toHttp(request))) {
                if (log.isDebugEnabled()) {
                    log.debug("用户: [{}] 请求 restful url : {}, 无权限被拦截.", subject.getPrincipal(), this.getPathWithinApplication(request));
                }

                WebHelper.writeJson(ResultBean.error("无权限"), response);
            } else {
                // 对于普通请求, 跳转到配置的 UnauthorizedUrl 页面.
                // 如果未设置 UnauthorizedUrl, 则返回 401 状态码
                String unauthorizedUrl = getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }

        }
        return false;
    }
}