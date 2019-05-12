package im.zhaojun.system.controller;

import im.zhaojun.common.util.ResultBean;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 安全相关 Controller
 */
@Controller
@RequestMapping("/security")
public class SecurityController {

    /**
     * 判断当前登录用户是否有某权限
     */
    @GetMapping("/hasPermission/{perms}")
    @ResponseBody
    public ResultBean hasPermission(@PathVariable("perms") String perms) {
        return ResultBean.success(SecurityUtils.getSubject().isPermitted(perms));
    }

}
