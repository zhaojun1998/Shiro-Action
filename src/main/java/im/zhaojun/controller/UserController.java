package im.zhaojun.controller;

import im.zhaojun.mapper.UserMapper;
import im.zhaojun.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/user")
    @ResponseBody
    public User get() {
        return userMapper.selectByPrimaryKey(1);
    }
}
