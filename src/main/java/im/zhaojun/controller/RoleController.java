package im.zhaojun.controller;

import com.github.pagehelper.PageInfo;
import im.zhaojun.model.Role;
import im.zhaojun.service.RoleService;
import im.zhaojun.util.PageResultBean;
import im.zhaojun.util.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/roles")
    public String index() {
        return "role/role-list";
    }

    @GetMapping(value = "/roles/list")
    @ResponseBody
    public PageResultBean<Role> getList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "limit", defaultValue = "10")int limit) {
        List<Role> roles = roleService.selectAll(page, limit);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        return new PageResultBean<>(rolePageInfo.getTotal(), rolePageInfo.getList());
    }

    @GetMapping("/role")
    public String add() {
        return "role/role-add";
    }

    @PostMapping("/role")
    @ResponseBody
    public ResultBean<Integer> add(Role role, @RequestParam("menuIds[]") Integer[] menuIds) {
        return new ResultBean<>(roleService.add(role, menuIds));
    }

    @GetMapping("/role/{id}")
    public String update(@PathVariable("id") Integer roleId, Model model) {
        Role role = roleService.selectOne(roleId);
        model.addAttribute("role", role);

        List<Integer> checkedKey = roleService.selectMenuIdByRoleId(roleId);
        model.addAttribute("checkedKey", checkedKey);
        return "role/role-add";
    }

    @PutMapping("/role")
    @ResponseBody
    public ResultBean<Integer> update(Role role, @RequestParam("menuIds[]") Integer[] menuIds) {
        return new ResultBean<>(roleService.update(role, menuIds));
    }
}
