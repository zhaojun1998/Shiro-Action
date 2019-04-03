package im.zhaojun.aop;

import im.zhaojun.service.ShiroService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class RefreshFilterChainAspect {

    @Resource
    private ShiroService shiroService;

    @Pointcut("@annotation(im.zhaojun.annotation.RefreshFilterChain)")
    public void updateFilterChain() {}

    @AfterReturning("updateFilterChain()")
    public void doAfter() {
        shiroService.updateFilterChain();
    }

}
