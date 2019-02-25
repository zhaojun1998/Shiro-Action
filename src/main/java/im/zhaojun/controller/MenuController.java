package im.zhaojun.controller;

import im.zhaojun.annotation.OperationLog;
import im.zhaojun.annotation.RefreshFilterChain;
import im.zhaojun.model.Menu;
import im.zhaojun.model.vo.RoleMenuVO;
import im.zhaojun.service.MenuService;
import im.zhaojun.util.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/menu/index")
    public String index() {
        return "menu/menu-list";
    }

    @OperationLog("获取菜单列表")
    @GetMapping("/menu/list")
    @ResponseBody
    public ResultBean getList(@RequestParam(required = false) Integer parentId) {
        List<Menu> menuList = menuService.selectByParentId(parentId);
        return ResultBean.success(menuList);
    }

    @GetMapping("/menu")
    public String getPage(Model model) {
        List<Menu> menus = menuService.selectAllMenu();
        return "menu/menu-add";
    }

    @OperationLog("获取菜单树形数据")
    @GetMapping("/menu/tree")
    @ResponseBody
    public ResultBean tree() {
        return ResultBean.success(menuService.getALLMenuTreeVO());
    }

    @OperationLog("获取菜单树形数据")
    @GetMapping("/menu/tree/operator")
    @ResponseBody
    public ResultBean menuAndCountOperatorTree() {
        return ResultBean.success(menuService.getALLMenuAndCountOperatorTreeVO());
    }

    @OperationLog("新增菜单")
    @RefreshFilterChain
    @PostMapping("/menu")
    @ResponseBody
    public ResultBean add(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        return ResultBean.success(menuService.add(menu));
    }

    @OperationLog("删除菜单")
    @RefreshFilterChain
    @DeleteMapping("/menu/{id}")
    @ResponseBody
    public ResultBean delete(@PathVariable("id") Integer id) {
        return ResultBean.success(menuService.deleteByIDAndChildren(id));
    }

    @GetMapping("/menu/{id}")
    public String updateMenu(@PathVariable("id") Integer id, Model model) {
        Menu menu = menuService.selectOne(id);
        model.addAttribute("menu", menu);
        return "menu/menu-add";
    }

    @OperationLog("修改菜单")
    @RefreshFilterChain
    @PutMapping("/menu")
    @ResponseBody
    public ResultBean update(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        return ResultBean.success(menuService.update(menu));
    }

    @GetMapping("/menu/{id}/allocation")
    public String allocation(@PathVariable("id") Integer menuId, Model model) {
        List<RoleMenuVO> roleMenuVOS = menuService.selectAllRoleByMenuId(menuId);
        model.addAttribute("roles", roleMenuVOS);
        model.addAttribute("menuId", menuId);
        return "menu/role-allocation";
    }

    @RefreshFilterChain
    @PostMapping("/menu/{id}/allocation/role")
    @ResponseBody
    public ResultBean allocation(@PathVariable("id") Integer menuId, @RequestParam("role[]") Integer roleIds[]) {
        List<RoleMenuVO> roleMenuVOS = menuService.selectAllRoleByMenuId(menuId);
        menuService.allocationRole(menuId, roleIds);
        return ResultBean.success();
    }

    @OperationLog("菜单交换顺序")
    @PostMapping("/menu/swap")
    @ResponseBody
    public ResultBean swapSort(Integer currentId, Integer swapId) {
        menuService.swapSort(currentId, swapId);
        return ResultBean.success();
    }
}