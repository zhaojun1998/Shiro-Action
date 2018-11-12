package im.zhaojun.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 修改后的 perms 过滤器
 */
public class MyAuthorizationFilter extends PermissionsAuthorizationFilter {

    private static final Logger log = LoggerFactory
            .getLogger(MyAuthorizationFilter.class);

    /**
     * 当没有权限被拦截时:
     *          如果是 AJAX 请求, 则返回 JSON 数据.
     *          如果是普通请求, 则跳转到配置 UnauthorizedUrl 页面.
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        // 如果未登录
        if (subject.getPrincipal() == null) {
            // AJAX 请求返回 JSON
            if (im.zhaojun.util.WebUtils.isAjaxRequest(WebUtils.toHttp(request))) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", -1);
                im.zhaojun.util.WebUtils.writeJson(map, response);
            } else {
                saveRequestAndRedirectToLogin(request, response);
            }
        } else {
            // 如果已登陆, 但没有权限
            // 对于 AJAX 请求返回 JSON
            if (im.zhaojun.util.WebUtils.isAjaxRequest(WebUtils.toHttp(request))) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", -2);
                map.put("msg", "没有权限啊!");
                im.zhaojun.util.WebUtils.writeJson(map, response);
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
