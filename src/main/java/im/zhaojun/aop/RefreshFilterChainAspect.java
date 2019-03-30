package im.zhaojun.aop;

import im.zhaojun.service.ShiroService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class RefreshFilterChainAspect {

    @Resource
    private ShiroService shiroService;

    private static final Logger log = LoggerFactory.getLogger(RefreshFilterChainAspect.class);

    @Pointcut("@annotation(im.zhaojun.annotation.RefreshFilterChain)")
    public void updateFilterChain() {}

    @AfterReturning("updateFilterChain()")
    public void doAfter() {
        shiroService.updateFilterChain();
    }

}
