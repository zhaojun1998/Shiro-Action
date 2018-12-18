package im.zhaojun.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.mail.MailUtil;
import im.zhaojun.exception.CaptchaIncorrectException;
import im.zhaojun.exception.UserAlreadyExistsException;
import im.zhaojun.model.User;
import im.zhaojun.service.UserService;
import im.zhaojun.util.ResultBean;
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
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

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
        if (!realCaptcha.equals(captcha)) {
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
                MailUtil.send(user.getEmail(), "Shiro-Action 激活", mailContent, false))
                .start();

        // 注册后默认的角色, 根据自己数据库的角色表 ID 设置
        Integer[] initRoleIds = {2};
        return new ResultBean<>(userService.add(user, initRoleIds));
    }

    @GetMapping("captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha shearCaptcha = CaptchaUtil.createCircleCaptcha(160, 38, 4, 0);

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("captcha", shearCaptcha.getCode());

        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(shearCaptcha.getImage(), "png", os);
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
