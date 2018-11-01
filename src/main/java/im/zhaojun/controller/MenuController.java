package im.zhaojun.controller;

import im.zhaojun.model.Menu;
import im.zhaojun.service.MenuService;
import im.zhaojun.util.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Controller
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Resource
    private MenuService menuService;

    @GetMapping("/menus")
    public String listMenuHtml() {
        return "menu-list";
    }

    @GetMapping("/menus/tree")
    @ResponseBody
    public ResultBean<Collection<Menu>> getAll() {
        return new ResultBean<>(menuService.selectAll());
    }

    @GetMapping("/menu")
    public String menuHTML(Model model) {
        List<Menu> menus = menuService.selectAllMenu();
        model.addAttribute("menus", menus);
        return "menu-add";
    }

    @PostMapping("/menu")
    @ResponseBody
    public ResultBean<Integer> add(Menu menu) {
        logger.debug("add menu: {}", menu);
        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        return new ResultBean<>(menuService.add(menu));
    }

    @DeleteMapping("/menu/{id}")
    @ResponseBody
    public ResultBean<Boolean> delete(@PathVariable("id") Integer id) {
        return new ResultBean<>(menuService.deleteByIDAndChildren(id));
    }

    @GetMapping("/menu/{id}")
    public String editHtml(@PathVariable("id") Integer id, Model model) {
        Menu menu = menuService.selectOne(id);
        List<Menu> menus = menuService.selectAllMenu();
        model.addAttribute("menu", menu);
        model.addAttribute("menus", menus);
        return "menu-add";
    }

    @PutMapping("/menu")
    @ResponseBody
    public ResultBean<Boolean> edit(Menu menu) {
        logger.debug("edit menu: {}", menu);
        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        return new ResultBean<>(menuService.update(menu));
    }
}
