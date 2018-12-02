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
public class updateFilterChainAspect {

    @Resource
    private ShiroService shiroService;

    private static final Logger logger = LoggerFactory.getLogger(updateFilterChainAspect.class);

    @Pointcut("@annotation(im.zhaojun.annotaion.UpdateFilterChain)")
    public void updateFilterChain() {}

    @After("updateFilterChain()")
    public void doAfter() {
        logger.info("更新 Shiro 过滤器链");
        shiroService.updateFilterChain();
    }

}
