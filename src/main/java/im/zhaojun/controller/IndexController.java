package im.zhaojun.controller;

import im.zhaojun.model.Menu;
import im.zhaojun.model.vo.MenuTreeVO;
import im.zhaojun.service.MenuService;
import im.zhaojun.util.MenuVOConvert;
import im.zhaojun.util.TreeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private MenuService menuService;

    @RequestMapping(value = {"/", "/main"})
    public String index(Model model) {
        List<Menu> menuTreeVO = menuService.selectAllMenu();
        List<MenuTreeVO> menuTreeVOS = new ArrayList<>();
        for (Menu menu : menuTreeVO) {
            menuTreeVOS.add(MenuVOConvert.menuToTreeVO(menu));
        }
        model.addAttribute("menus", TreeUtil.toTree(menuTreeVOS));
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
