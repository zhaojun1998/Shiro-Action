package im.zhaojun.controller;

import im.zhaojun.annotation.UpdateFilterChain;
import im.zhaojun.model.Operator;
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

    @GetMapping("index")
    public String index() {
        return "operator/operator-list";
    }

    @GetMapping
    public String add() {
        return "operator/operator-add";
    }

    @UpdateFilterChain
    @PostMapping
    @ResponseBody
    public ResultBean add(Operator operator) {
        operatorService.insert(operator);
        return new ResultBean<>();
    }

    @GetMapping("{id}")
    public String edit(Model model, @PathVariable("id") Integer operatorId) {
        Operator operator = operatorService.selectByPrimaryKey(operatorId);
        model.addAttribute("operator", operator);
        return "operator/operator-add";
    }

    @UpdateFilterChain
    @PutMapping
    @ResponseBody
    public ResultBean edit(Operator operator) {
        operatorService.updateByPrimaryKey(operator);
        return new ResultBean<>();
    }

    @GetMapping("list")
    @ResponseBody
    public ResultBean getList(@RequestParam(required = false) Integer menuId) {
        List<Operator> operatorList = operatorService.selectByMenuId(menuId);
        return new ResultBean<>(operatorList);
    }

    @UpdateFilterChain
    @DeleteMapping("{id}")
    @ResponseBody
    public ResultBean delete(@PathVariable("id") Integer operatorId) {
        operatorService.deleteByPrimaryKey(operatorId);
        return new ResultBean();
    }

}
