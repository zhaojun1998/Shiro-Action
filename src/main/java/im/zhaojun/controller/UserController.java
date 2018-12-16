package im.zhaojun.controller;

import com.github.pagehelper.PageInfo;
import im.zhaojun.model.User;
import im.zhaojun.service.RoleService;
import im.zhaojun.service.UserService;
import im.zhaojun.util.PageResultBean;
import im.zhaojun.util.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @GetMapping("/users")
    public String index() {
        return "user/user-list";
    }

    @GetMapping("/users/page")
    @ResponseBody
    public PageResultBean<User> getList(@RequestParam(value = "page", defaultValue = "1") int page,
                                          @RequestParam(value = "limit", defaultValue = "10")int limit) {
        List<User> users = userService.selectAll(page, limit);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        return new PageResultBean<>(userPageInfo.getTotal(), userPageInfo.getList());
    }


    @GetMapping("/user")
    public String add(Model model) {
        model.addAttribute("roles", roleService.selectAll());
        return "user/user-add";
    }

    @PostMapping("/user")
    @ResponseBody
    public ResultBean<Integer> add(User user,  @RequestParam("role[]") Integer roleIds[]) {
        return new ResultBean<>(userService.add(user, roleIds));
    }

    @GetMapping("/user/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        User user = userService.selectOne(id);
        model.addAttribute("user", user);
        return "user/user-add";
    }

    @GetMapping("/user/{id}/allocation")
    public String allocation(@PathVariable("id") Integer userId, Model model) {
        User user = userService.selectOne(userId);
        model.addAttribute("roleIds", userService.selectRoleIdsById(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("roles", roleService.selectAll());
        return "user/user-allocation";
    }

    @PostMapping("/user/{id}/allocation")
    @ResponseBody
    public ResultBean allocation(@PathVariable("id") Integer userId,
                                         @RequestParam("role[]") Integer roleIds[]) {
        userService.allocation(userId, roleIds);
        return new ResultBean();
    }

    @PostMapping("/user/{id}/disable")
    @ResponseBody
    public ResultBean<Boolean> disable(@PathVariable("id") Integer id) {
        return new ResultBean<>(userService.disableUserByID(id));
    }

    @PostMapping("/user/{id}/enable")
    @ResponseBody
    public ResultBean<Boolean> enable(@PathVariable("id") Integer id) {
        return new ResultBean<>(userService.enableUserByID(id));
    }
}