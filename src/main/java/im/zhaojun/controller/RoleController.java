package im.zhaojun.controller;

import com.github.pagehelper.PageInfo;
import im.zhaojun.annotation.OperationLog;
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
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/index")
    public String index() {
        return "role/role-list";
    }

    @OperationLog("获取角色列表")
    @GetMapping("/list")
    @ResponseBody
    public PageResultBean<Role> getList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "limit", defaultValue = "10")int limit) {
        List<Role> roles = roleService.selectAll(page, limit);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        return new PageResultBean<>(rolePageInfo.getTotal(), rolePageInfo.getList());
    }

    @GetMapping
    public String add() {
        return "role/role-add";
    }

    @OperationLog("新增角色")
    @PostMapping
    @ResponseBody
    public ResultBean add(Role role) {
        roleService.add(role);
        return ResultBean.success();
    }

    @GetMapping("/{roleId}")
    public String update(@PathVariable("roleId") Integer roleId, Model model) {
        Role role = roleService.selectOne(roleId);
        model.addAttribute("role", role);
        return "role/role-add";
    }

    @OperationLog("修改角色")
    @PutMapping
    @ResponseBody
    public ResultBean update(Role role) {
        roleService.update(role);
        return ResultBean.success();
    }


    @OperationLog("删除角色")
    @DeleteMapping("/{roleId}")
    @ResponseBody
    public ResultBean delete(@PathVariable("roleId") Integer roleId) {
        roleService.delete(roleId);
        return ResultBean.success();
    }

    @OperationLog("为角色授予菜单")
    @PostMapping("/{roleId}/grant/menu")
    @ResponseBody
    public ResultBean grantMenu(@PathVariable("roleId") Integer roleId, @RequestParam("menuIds[]") Integer[] menuIds) {
        roleService.grantMenu(roleId, menuIds);
        return ResultBean.success();
    }

    @OperationLog("为角色授予菜单")
    @PostMapping("/{roleId}/grant/operator")
    @ResponseBody
    public ResultBean grantOperator(@PathVariable("roleId") Integer roleId, @RequestParam("operatorIds[]") Integer[] operatorIds) {
        roleService.grantOperator(roleId, operatorIds);
        return ResultBean.success();
    }
    
    @GetMapping("/{roleId}/own/menu")
    @ResponseBody
    public ResultBean getRoleOwnMenu(@PathVariable("roleId") Integer roleId) {
        return ResultBean.success(roleService.getMenusByRoleId(roleId));
    }

    @GetMapping("/{roleId}/own/operator")
    @ResponseBody
    public ResultBean getOperatorOwnMenu(@PathVariable("roleId") Integer roleId) {
        return ResultBean.success(roleService.getOperatorsByRoleId(roleId));
    }
}
