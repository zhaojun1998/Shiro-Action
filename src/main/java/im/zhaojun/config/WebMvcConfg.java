package im.zhaojun.config;

import im.zhaojun.interceptor.LogHandlerInterceptor;
import im.zhaojun.interceptor.ShiroMDCInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    @Resource
    private LogHandlerInterceptor logHandlerInterceptor;

    @Resource
    private ShiroMDCInterceptor shiroMDCInterceptor;

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(shiroMDCInterceptor).excludePathPatterns(Arrays.asList("/css/**", "/fonts/**", "/images/**", "/js/**", "/lib/**", "/error"));
        registry.addInterceptor(logHandlerInterceptor).excludePathPatterns(Arrays.asList("/css/**", "/fonts/**", "/images/**", "/js/**", "/lib/**", "/error"));
    }


}