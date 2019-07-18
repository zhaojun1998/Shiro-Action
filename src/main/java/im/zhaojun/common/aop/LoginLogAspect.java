package im.zhaojun.common.aop;

import im.zhaojun.common.util.IPUtils;
import im.zhaojun.system.model.User;
import im.zhaojun.system.service.LoginLogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
@ConditionalOnProperty(value = "shiro-action.log.login", havingValue = "true")
public class LoginLogAspect {

    @Resource
    private LoginLogService loginLogService;

    @Pointcut("execution(im.zhaojun.common.util.ResultBean im.zhaojun.system.controller..LoginController.login(im.zhaojun.system.model.User, String) )")
    public void loginLogPointCut() {}

    @After("loginLogPointCut()")
    public void recordLoginLog(JoinPoint joinPoint) {
        // 获取登陆参数
        Object[] args = joinPoint.getArgs();
        User user = (User) args[0];

        Subject subject = SecurityUtils.getSubject();

        String ip = IPUtils.getIpAddr();
        loginLogService.addLog(user.getUsername(), subject.isAuthenticated(), ip);
    }
}