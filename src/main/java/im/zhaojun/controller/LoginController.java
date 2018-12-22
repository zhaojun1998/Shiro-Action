package im.zhaojun.controller;

import cn.hutool.core.util.IdUtil;
import im.zhaojun.exception.CaptchaIncorrectException;
import im.zhaojun.exception.UserAlreadyExistsException;
import im.zhaojun.model.User;
import im.zhaojun.service.UserService;
import im.zhaojun.util.ResultBean;
import im.zhaojun.util.code.Captcha;
import im.zhaojun.util.code.GifCaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private MailService mailService;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("login")
    @ResponseBody
    public ResultBean<Boolean> login(User user, String captcha) {
        Subject subject = SecurityUtils.getSubject();
        String realCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute("captcha");

        // session 中的验证码过期了
        if (realCaptcha == null || realCaptcha.equals(captcha.toLowerCase()) == false) {
            throw new CaptchaIncorrectException();
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        subject.login(token);
        return new ResultBean<>(userService.updateLastLoginTimeByUsername(user.getUsername()));
    }

    @GetMapping("logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:login";
    }

    @GetMapping("checkUser")
    @ResponseBody
    public ResultBean<Boolean> checkUser(String username) {
        return new ResultBean<>(userService.checkUserNameExist(username));
    }

    @PostMapping("register")
    @ResponseBody
    public ResultBean<Integer> register(User user) {
        if (userService.checkUserNameExist(user.getUsername())) {
            throw new UserAlreadyExistsException();
        }
        String activeCode = IdUtil.fastSimpleUUID();
        user.setActiveCode(activeCode);
        user.setStatus("0");
        String mailContent = "感谢您注册 Shiro-Action, 请验证您的邮箱，链接为： http://localhost/active?token=" + activeCode;

        new Thread(() ->
                mailService.sendSimpleMail(user.getEmail(), "Shiro-Action 激活邮件", mailContent))
                .start();

        // 注册后默认的角色, 根据自己数据库的角色表 ID 设置
        Integer[] initRoleIds = {2};
        return new ResultBean<>(userService.add(user, initRoleIds));
    }

    @GetMapping("captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        Captcha captcha = new GifCaptcha(160, 38, 4);

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpg");

        captcha.out(response.getOutputStream());

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("captcha", captcha.text().toLowerCase());
    }

    @GetMapping("active")
    public String active(String token, Model model) {
        User user = userService.selectByActiveCode(token);
        String msg = "";
        if (user == null) {
            msg = "请求异常, 激活地址不存在!";
        } else if ("1".equals(user.getStatus())) {
            msg = "用户已激活, 请勿重复激活!";
        } else {
            msg = "激活成功!";
            user.setStatus("1");
            userService.update(user);
        }
        model.addAttribute("msg", msg);
        return "active";
    }
}
