package im.zhaojun.controller;

import im.zhaojun.model.vo.MenuTreeVO;
import im.zhaojun.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private MenuService menuService;

    @GetMapping(value = {"/", "/main"})
    public String index(Model model) {
        List<MenuTreeVO> menuTreeVOS = menuService.getCurrentUserMenuTreeVO();
        model.addAttribute("menus", menuTreeVOS);
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/403")
    public String unauthorizedPage() {
        return "403";
    }
}
