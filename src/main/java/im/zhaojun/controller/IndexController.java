package im.zhaojun.controller;

import im.zhaojun.model.vo.MenuTreeVO;
import im.zhaojun.service.LoginLogService;
import im.zhaojun.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private MenuService menuService;

    @Resource
    private LoginLogService loginLogService;

    @GetMapping(value = {"/", "/main"})
    public String index(Model model) {
        List<MenuTreeVO> menuTreeVOS = menuService.selectCurrentUserMenuTreeVO();
        model.addAttribute("menus", menuTreeVOS);
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


    @GetMapping("/weekLoginCount")
    @ResponseBody
    public List<Integer> recentlyWeekLoginCount() {
        return loginLogService.recentlyWeekLoginCount();
    }
}
