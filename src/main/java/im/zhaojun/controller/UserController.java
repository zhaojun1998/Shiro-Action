package im.zhaojun.controller;

import com.github.pagehelper.PageInfo;
import im.zhaojun.model.User;
import im.zhaojun.model.vo.UserVO;
import im.zhaojun.service.UserService;
import im.zhaojun.util.PageResultBean;
import im.zhaojun.util.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/users")
    public String index() {
        return "user-list";
    }

    @GetMapping("/users/page")
    @ResponseBody
    public PageResultBean<UserVO> getList(@RequestParam(value = "page", defaultValue = "1") int page,
                                          @RequestParam(value = "limit", defaultValue = "10")int limit) {
        List<UserVO> users = userService.selectAll(page, limit);
        PageInfo<UserVO> userPageInfo = new PageInfo<>(users);
        return new PageResultBean<>(userPageInfo.getTotal(), userPageInfo.getList());
    }


    @GetMapping("/user")
    public String add() {
        return "user-add";
    }

    @PostMapping("/user")
    @ResponseBody
    public ResultBean<Integer> add(User user) {
        return new ResultBean<>(userService.add(user));
    }

    @PostMapping("/user/disable")
    @ResponseBody
    public ResultBean<Boolean> disable(Integer id) {
        return new ResultBean<>(userService.disableUserByID(id));
    }

    @PostMapping("/user/enable")
    @ResponseBody
    public ResultBean<Boolean> enable(Integer id) {
        return new ResultBean<>(userService.enableUserByID(id));
    }
}
