package im.zhaojun.controller;

import im.zhaojun.model.vo.MenuTreeVO;
import im.zhaojun.service.MenuService;
import im.zhaojun.util.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
public class MenuController {

    @Resource
    private MenuService menuService;

    @RequestMapping("/menus")
    public String listMenuHtml() {
        return "menu-list";
    }

    @GetMapping("/menus/tree")
    @ResponseBody
    public ResultBean<Collection<MenuTreeVO>> menuTree() {
        return new ResultBean<>(menuService.getAllMenuTreeVO());
    }
}
