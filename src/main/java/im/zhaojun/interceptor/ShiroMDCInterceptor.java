package im.zhaojun.interceptor;

import im.zhaojun.model.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ShiroMDCInterceptor implements HandlerInterceptor {
 
    private final static String MDC_USERNAME = "username";
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroMDCInterceptor.class);
 
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LOGGER.error(httpServletRequest.getRequestURI());
        // 如已进行登录, 则获取当前登录者的用户名放入 MDC 中.
        String username = "";
        if (SecurityUtils.getSubject().getPrincipal() != null) {
            username = ((User) SecurityUtils.getSubject().getPrincipal()).getUsername();
        }
        MDC.put(MDC_USERNAME, username);
//        LOGGER.debug("MDC : PUT MDC_USERNAME ({}) in logger", username);
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String username = MDC.get(MDC_USERNAME);
//        LOGGER.debug("MDC : remove MDC_USERNAME ({}) from logger", username);
        MDC.remove(username);
    }
 
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
 
    }
}
