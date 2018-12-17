package im.zhaojun.controller;

import im.zhaojun.util.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonPageController {

    @GetMapping("/403")
    public String forbidden() {
        return "common/403";
    }

    @GetMapping("/404")
    public String unauthorizedPage() {
        return "common/404";
    }

    @GetMapping("/500")
    public String error() {
        return "common/500";
    }
}