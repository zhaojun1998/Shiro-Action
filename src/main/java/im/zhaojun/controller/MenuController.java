package im.zhaojun.controller;

import im.zhaojun.annotation.OperationLog;
import im.zhaojun.annotation.UpdateFilterChain;
import im.zhaojun.model.Menu;
import im.zhaojun.model.vo.MenuTreeVO;
import im.zhaojun.model.vo.RoleMenuVO;
import im.zhaojun.service.MenuService;
import im.zhaojun.util.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
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
    public ResultBean<Collection<Menu>> getList() {
        return new ResultBean<>(menuService.selectAll());
    }

    @GetMapping("/menu")
    public String getMenu(Model model) {
        List<Menu> menus = menuService.selectAllMenuAndPage();
        model.addAttribute("menus", menus);
        return "menu/menu-add";
    }

    @GetMapping("/menu/page")
    public String getPage(Model model) {
        List<Menu> menus = menuService.selectAllMenuAndPage();
        model.addAttribute("menus", menus);
        return "menu/page-add";
    }

    @GetMapping("/menu/api")
    public String getAPI(Model model) {
        List<Menu> menus = menuService.selectAllMenuAndPage();
        model.addAttribute("menus", menus);
        return "menu/api-add";
    }

    @OperationLog("获取菜单列表")
    @GetMapping("/menu/tree")
    @ResponseBody
    public List<MenuTreeVO> tree() {
        return menuService.getALLMenuTreeVO();
    }

    @OperationLog("新增菜单")
    @UpdateFilterChain
    @PostMapping("/menu")
    @ResponseBody
    public ResultBean<Integer> add(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        return new ResultBean<>(menuService.add(menu));
    }

    @OperationLog("删除菜单")
    @UpdateFilterChain
    @DeleteMapping("/menu/{id}")
    @ResponseBody
    public ResultBean<Boolean> delete(@PathVariable("id") Integer id) {
        return new ResultBean<>(menuService.deleteByIDAndChildren(id));
    }

    @GetMapping("/menu/{id}")
    public String updateMenu(@PathVariable("id") Integer id, Model model) {
        Menu menu = menuService.selectOne(id);
        model.addAttribute("menu", menu);
        return "menu/menu-add";
    }

    @GetMapping("/menu/page/{id}")
    public String updatePage(@PathVariable("id") Integer id, Model model) {
        Menu menu = menuService.selectOne(id);
        model.addAttribute("menu", menu);
        return "menu/page-add";
    }

    @GetMapping("/menu/api/{id}")
    public String updateAPI(@PathVariable("id") Integer id, Model model) {
        Menu menu = menuService.selectOne(id);
        model.addAttribute("menu", menu);
        return "menu/api-add";
    }

    @OperationLog("修改菜单")
    @UpdateFilterChain
    @PutMapping("/menu")
    @ResponseBody
    public ResultBean<Boolean> update(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        return new ResultBean<>(menuService.update(menu));
    }


    @GetMapping("/menu/{id}/allocation")
    public String allocation(@PathVariable("id") Integer menuId, Model model) {
        List<RoleMenuVO> roleMenuVOS = menuService.selectAllRoleByMenuId(menuId);
        model.addAttribute("roles", roleMenuVOS);
        model.addAttribute("menuId", menuId);
        return "menu/role-allocation";
    }


    @PostMapping("/menu/{id}/allocation/role")
    @ResponseBody
    public ResultBean allocation(@PathVariable("id") Integer menuId, @RequestParam("role[]") Integer roleIds[]) {
        List<RoleMenuVO> roleMenuVOS = menuService.selectAllRoleByMenuId(menuId);
        menuService.allocationRole(menuId, roleIds);
        return new ResultBean();
    }
}