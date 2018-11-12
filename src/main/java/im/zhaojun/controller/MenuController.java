package im.zhaojun.controller;

import im.zhaojun.annotaion.UpdateFilterChain;
import im.zhaojun.model.Menu;
import im.zhaojun.service.MenuService;
import im.zhaojun.service.ShiroService;
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

    @Resource
    private ShiroService shiroService;

    @GetMapping("/menus")
    public String index() {
        return "menu-list";
    }

    @GetMapping("/menus/list")
    @ResponseBody
    public ResultBean<Collection<Menu>> getList() {
        return new ResultBean<>(menuService.selectAll());
    }

    @GetMapping("/menu")
    public String get(Model model) {
        List<Menu> menus = menuService.selectAllMenu();
        model.addAttribute("menus", menus);
        return "menu-add";
    }

    @UpdateFilterChain
    @PostMapping("/menu")
    @ResponseBody
    public ResultBean<Integer> add(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        return new ResultBean<>(menuService.add(menu));
    }

    @UpdateFilterChain
    @DeleteMapping("/menu/{id}")
    @ResponseBody
    public ResultBean<Boolean> delete(@PathVariable("id") Integer id) {
        return new ResultBean<>(menuService.deleteByIDAndChildren(id));
    }

    @GetMapping("/menu/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        Menu menu = menuService.selectOne(id);
        List<Menu> menus = menuService.selectAllMenu();
        model.addAttribute("menu", menu);
        model.addAttribute("menus", menus);
        return "menu-add";
    }

    @UpdateFilterChain
    @PutMapping("/menu")
    @ResponseBody
    public ResultBean<Boolean> update(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        return new ResultBean<>(menuService.update(menu));
    }
}
