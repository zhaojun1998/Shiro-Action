package im.zhaojun.system.controller;

import com.github.pagehelper.PageInfo;
import im.zhaojun.common.annotation.OperationLog;
import im.zhaojun.common.util.PageResultBean;
import im.zhaojun.common.util.ResultBean;
import im.zhaojun.system.model.Role;
import im.zhaojun.system.service.RoleService;
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

    @OperationLog("查询角色列表")
    @GetMapping("/list")
    @ResponseBody
    public PageResultBean<Role> getList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "limit", defaultValue = "10")int limit,
                                        Role roleQuery) {
        List<Role> roles = roleService.selectAll(page, limit, roleQuery);
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
    public ResultBean grantMenu(@PathVariable("roleId") Integer roleId, @RequestParam(value = "menuIds[]", required = false) Integer[] menuIds) {
        roleService.grantMenu(roleId, menuIds);
        return ResultBean.success();
    }


    @OperationLog("为角色授予操作权限")
    @PostMapping("/{roleId}/grant/operator")
    @ResponseBody
    public ResultBean grantOperator(@PathVariable("roleId") Integer roleId, @RequestParam(value = "operatorIds[]", required = false) Integer[] operatorIds) {
        roleService.grantOperator(roleId, operatorIds);
        return ResultBean.success();
    }

    /**
     * 获取角色拥有的菜单
     */
    @GetMapping("/{roleId}/own/menu")
    @ResponseBody
    public ResultBean getRoleOwnMenu(@PathVariable("roleId") Integer roleId) {
        return ResultBean.success(roleService.getMenusByRoleId(roleId));
    }

    /**
     * 获取角色拥有的操作权限
     */
    @GetMapping("/{roleId}/own/operator")
    @ResponseBody
    public ResultBean getRoleOwnOperator(@PathVariable("roleId") Integer roleId) {
        Integer[] operatorIds = roleService.getOperatorsByRoleId(roleId);
        for (int i = 0; i < operatorIds.length; i++) {
            operatorIds[i] = operatorIds[i] + 10000;
        }
        return ResultBean.success(operatorIds);
    }
}
