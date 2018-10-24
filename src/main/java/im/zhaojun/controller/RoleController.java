package im.zhaojun.controller;

import com.github.pagehelper.PageInfo;
import im.zhaojun.model.Role;
import im.zhaojun.service.RoleService;
import im.zhaojun.util.PageResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RoleController {

    @Resource
    private RoleService roleService;

    @RequestMapping("/roles")
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

}
