package im.zhaojun.common.exception;

import im.zhaojun.common.util.ResultBean;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class WebExceptionHandler {

    @Resource
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler
    public String authcTypeNotSupport(AuthcTypeNotSupportException e) {
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        }
        return generateErrorInfo(ResultBean.FAIL, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler
    public String unauthenticatedException(UnauthenticatedException e) {
        return "redirect:" + shiroFilterFactoryBean.getLoginUrl();
    }

    @ExceptionHandler
    public String unauthorized(NoHandlerFoundException e) {
        if (log.isDebugEnabled()) {
            log.debug("请求的地址不存在", e);
        }
        return generateErrorInfo(ResultBean.FAIL, "请求的地址不存在", HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public String unauthorized(Exception e) {
        if (log.isDebugEnabled()) {
            log.debug("无权限");
        }
        return generateErrorInfo(ResultBean.FAIL, "无权限");
    }

    @ExceptionHandler
    public String unknownAccount(UnknownAccountException e) {
        if (log.isDebugEnabled()) {
            log.debug("账号不存在");
        }
        return generateErrorInfo(ResultBean.FAIL, "账号不存在");
    }

    @ExceptionHandler
    public String incorrectCredentials(IncorrectCredentialsException e) {
        if (log.isDebugEnabled()) {
            log.debug("密码错误");
        }
        return generateErrorInfo(ResultBean.FAIL, "密码错误");
    }

    @ExceptionHandler
    public String excessiveAttemptsException(ExcessiveAttemptsException e) {
        if (log.isDebugEnabled()) {
            log.debug("登录失败次数过多");
        }
        return generateErrorInfo(ResultBean.FAIL, "登录失败次数过多, 请稍后再试");
    }

    @ExceptionHandler
    public String lockedAccount(LockedAccountException e) {
        if (log.isDebugEnabled()) {
            log.debug("账号已锁定");
        }
        return generateErrorInfo(ResultBean.FAIL, "账号已锁定");
    }

    @ExceptionHandler
    public String lockedAccount(CaptchaIncorrectException e) {
        if (log.isDebugEnabled()) {
            log.debug("验证码错误");
        }
        return generateErrorInfo(ResultBean.FAIL, "验证码错误");
    }

    @ExceptionHandler
    public String lockedAccount(DuplicateNameException e) {
        if (log.isDebugEnabled()) {
            log.debug("用户名已存在");
        }
        return generateErrorInfo(ResultBean.FAIL, "用户名已存在");
    }

    @ExceptionHandler
    public String missingRequestParameter(MissingServletRequestParameterException e) {
        if (log.isDebugEnabled()) {
            log.debug("请求参数无效");
        }
        return generateErrorInfo(ResultBean.FAIL, "请求参数缺失");
    }

    @ExceptionHandler
    public String methodArgumentNotValid(BindException e) {
        if (log.isDebugEnabled()) {
            log.debug("参数校验失败", e);
        }
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (int i = 0; i < allErrors.size(); i++) {
            ObjectError error = allErrors.get(i);
            errorMessage.append(error.getDefaultMessage());
            if (i != allErrors.size() - 1) {
                errorMessage.append(",");
            }
        }
        return generateErrorInfo(ResultBean.FAIL, errorMessage.toString());
    }

    @ExceptionHandler
    public String all(Exception e) {
        String msg = e.getMessage() == null ? "系统出现异常" : e.getMessage();
        log.error(msg, e);
        generateErrorInfo(ResultBean.FAIL, msg, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "forward:/error";
    }

    /**
     * 生成错误信息, 放到 request 域中.
     *
     * @param code       错误码
     * @param msg        错误信息
     * @param httpStatus HTTP 状态码
     * @return SpringBoot 默认提供的 /error Controller 处理器
     */
    private String generateErrorInfo(int code, String msg, int httpStatus) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute("code", code);
        request.setAttribute("msg", msg);
        request.setAttribute("javax.servlet.error.status_code", httpStatus);
        return "forward:/error";
    }


    /**
     * 捕获 ClientAbortException 异常, 不做任何处理, 防止出现大量堆栈日志输出, 此异常不影响功能.
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class, ClientAbortException.class})
    @ResponseBody
    @ResponseStatus
    public void clientAbortException(Exception ex) {
        if (log.isDebugEnabled()) {
            log.debug("出现了断开异常:", ex);
        }
    }

    private String generateErrorInfo(int code, String msg) {
        return generateErrorInfo(code, msg, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
