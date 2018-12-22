package im.zhaojun.aop;

import im.zhaojun.service.ShiroService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class UpdateFilterChainAspect {

    @Resource
    private ShiroService shiroService;

    private static final Logger log = LoggerFactory.getLogger(UpdateFilterChainAspect.class);

    @Pointcut("@annotation(im.zhaojun.annotation.UpdateFilterChain)")
    public void updateFilterChain() {}

    @After("updateFilterChain()")
    public void doAfter() {
        log.info("更新 Shiro 过滤器链");
        shiroService.updateFilterChain();
    }

}
