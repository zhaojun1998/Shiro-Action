package im.zhaojun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonPageController {

    @GetMapping("/403")
    public String unauthorizedPage() {
        return "403";
    }

    @GetMapping("/500")
    public String error() {
        return "500";
    }
}
