package im.zhaojun.config;

import im.zhaojun.interceptor.ShiroMDCInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    /**
     * MDC 拦截器
     */
    @Bean
    ShiroMDCInterceptor shiroMDCInterceptor() {
        return new ShiroMDCInterceptor();
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(shiroMDCInterceptor()).excludePathPatterns(Arrays.asList("/css/**", "/fonts/**", "/images/**", "/js/**", "/lib/**", "/error"));
    }

}