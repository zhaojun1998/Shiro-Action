package im.zhaojun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonPageController {

    @GetMapping("/403")
    public String forbidden() {
        return "403";
    }

    @GetMapping("/401")
    public String unauthorizedPage() {
        return "401";
    }

    @GetMapping("/500")
    public String error() {
        return "500";
    }
}
