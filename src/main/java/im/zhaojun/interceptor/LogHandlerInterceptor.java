package im.zhaojun.interceptor;

import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogHandlerInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LogHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getRequestURI());
        log.info(JSONUtil.toJsonStr(request.getParameterMap()));
        return true;
    }
}
