package im.zhaojun.interceptor;

import im.zhaojun.model.User;
import im.zhaojun.util.IPUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ShiroMDCInterceptor implements HandlerInterceptor {
 
    private final static String MDC_USERNAME = "username";

    private final static String IP = "ip";


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        // 如已进行登录, 则获取当前登录者的用户名放入 MDC 中.
        String username = "";
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user != null) {
            username = user.getUsername();
        }

        MDC.put(IP, IPUtils.getIpAddr());
        MDC.put(MDC_USERNAME, username);
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
        String username = MDC.get(MDC_USERNAME);
        MDC.remove(username);
    }
}