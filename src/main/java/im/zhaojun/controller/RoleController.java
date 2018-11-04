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
import java.util.Collection;
import java.util.List;

@Controller
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/roles")
    public String listRoleHtml() {
        return "role-list";
    }

    @GetMapping(value = "/roles/page")
    @ResponseBody
    public PageResultBean<Role> roles(@RequestParam(value = "name", required = false) String name,
                                      int page,
                                      int limit) {
        List<Role> roles = roleService.findListByName(name, page, limit);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        return new PageResultBean<>(rolePageInfo.getTotal(), rolePageInfo.getList());
    }

    @GetMapping("role")
    public String addRoleHtml() {
        return "role-add";
    }


    @PostMapping("role")
    @ResponseBody
    public ResultBean<Integer> add(Role role, @RequestParam("menuIds[]") Integer[] menuIds) {
        return new ResultBean<>(roleService.addRole(role, menuIds));
    }

    @PutMapping("role")
    @ResponseBody
    public ResultBean<Integer> update(Role role, @RequestParam("menuIds[]") Integer[] menuIds) {
        return new ResultBean<>(roleService.updateRole(role, menuIds));
    }

    @GetMapping("role/{id}")
    public String editHtml(@PathVariable("id") Integer roleId, Model model) {
        Role role = roleService.selectOne(roleId);
        model.addAttribute("role", role);

        List<Integer> checkedKey = roleService.selectMenuIdByRoleId(roleId);
        model.addAttribute("checkedKey", checkedKey);
        return "role-add";
    }
}
