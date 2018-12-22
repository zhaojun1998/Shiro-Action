package im.zhaojun.controller;

import im.zhaojun.annotation.Log;
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

    @Log("访问我的桌面")
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @Log("查看近七日登录统计图")
    @GetMapping("/weekLoginCount")
    @ResponseBody
    public List<Integer> recentlyWeekLoginCount() {
        return loginLogService.recentlyWeekLoginCount();
    }
}
