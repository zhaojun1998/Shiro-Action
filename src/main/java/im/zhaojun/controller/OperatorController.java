package im.zhaojun.controller;

import im.zhaojun.annotation.RefreshFilterChain;
import im.zhaojun.model.Operator;
import im.zhaojun.service.MenuService;
import im.zhaojun.service.OperatorService;
import im.zhaojun.util.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/operator")
public class OperatorController {

    private static final Logger log = LoggerFactory.getLogger(OperatorController.class);

    @Resource
    private OperatorService operatorService;

    @Resource
    private MenuService menuService;

    @GetMapping("/index")
    public String index() {
        return "operator/operator-list";
    }

    @GetMapping
    public String add() {
        return "operator/operator-add";
    }

    @RefreshFilterChain
    @PostMapping
    @ResponseBody
    public ResultBean add(Operator operator) {
        operatorService.add(operator);
        return ResultBean.success();
    }

    @GetMapping("/{operatorId}")
    public String edit(Model model, @PathVariable("operatorId") Integer operatorId) {
        Operator operator = operatorService.selectByPrimaryKey(operatorId);
        model.addAttribute("operator", operator);
        return "operator/operator-add";
    }

    @RefreshFilterChain
    @PutMapping
    @ResponseBody
    public ResultBean edit(Operator operator) {
        operatorService.updateByPrimaryKey(operator);
        return ResultBean.success();
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultBean getList(@RequestParam(required = false) Integer menuId) {
        List<Operator> operatorList = operatorService.selectByMenuId(menuId);
        return ResultBean.success(operatorList);
    }

    @RefreshFilterChain
    @DeleteMapping("/{operatorId}")
    @ResponseBody
    public ResultBean delete(@PathVariable("operatorId") Integer operatorId) {
        operatorService.deleteByPrimaryKey(operatorId);
        return ResultBean.success();
    }


    @GetMapping("/tree")
    @ResponseBody
    public ResultBean tree() {
        return ResultBean.success(operatorService.getALLMenuAndOperatorTreeVO());
    }

}
