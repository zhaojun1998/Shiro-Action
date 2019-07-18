package im.zhaojun.common.aop;

import im.zhaojun.common.annotation.OperationLog;
import im.zhaojun.common.util.IPUtils;
import im.zhaojun.common.util.ShiroUtil;
import im.zhaojun.system.mapper.SysLogMapper;
import im.zhaojun.system.model.SysLog;
import im.zhaojun.system.model.User;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 操作日志切面.
 */
@Aspect
@Component
@ConditionalOnProperty(value = "shiro-action.log.operation", havingValue = "true")
public class OperationLogAspect {

    @Resource
    private SysLogMapper sysLogMapper;

    @Pointcut("@annotation(im.zhaojun.common.annotation.OperationLog)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        // 执行时长
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();

        // 获取注解上的操作描述
        OperationLog operationLogAnnotation = method.getAnnotation(OperationLog.class);
        if (operationLogAnnotation != null) {
            sysLog.setOperation(operationLogAnnotation.value());
        }

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        // 请求的方法参数
        Object[] args = joinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                params.append("  ").append(paramNames[i]).append(": ").append(args[i]);
            }
            sysLog.setParams(params.toString());
        }

        sysLog.setIp(IPUtils.getIpAddr());

        // 获取当前登录用户名
        if (SecurityUtils.getSubject().isAuthenticated()) {
            User user = ShiroUtil.getCurrentUser();
            sysLog.setUsername(user.getUsername());
        }

        sysLog.setTime((int) time);
        sysLogMapper.insert(sysLog);
    }
}