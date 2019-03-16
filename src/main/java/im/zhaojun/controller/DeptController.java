package im.zhaojun.controller;

import im.zhaojun.annotation.OperationLog;
import im.zhaojun.model.Dept;
import im.zhaojun.service.DeptService;
import im.zhaojun.util.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("/index")
    public String index() {
        return "dept/dept-list";
    }

    @OperationLog("获取部门列表")
    @GetMapping("/list")
    @ResponseBody
    public ResultBean getList(@RequestParam(required = false) Integer parentId) {
        List<Dept> deptList = deptService.selectByParentId(parentId);
        return ResultBean.success(deptList);
    }

    @GetMapping("/tree")
    @ResponseBody
    public ResultBean tree() {
        return ResultBean.success();
    }

    @OperationLog("新增部门")
    @PostMapping
    @ResponseBody
    public ResultBean add(Dept dept) {
        if (dept.getParentId() == null) {
            dept.setParentId(0);
        }
        deptService.insert(dept);
        return ResultBean.success();
    }

    @OperationLog("删除部门")
    @DeleteMapping("/{deptId}")
    @ResponseBody
    public ResultBean delete(@PathVariable("deptId") Integer deptId) {
        deptService.deleteByIDAndChildren(deptId);
        return ResultBean.success();
    }

    @OperationLog("修改部门")
    @PutMapping
    @ResponseBody
    public ResultBean update(Dept dept) {
        if (dept.getParentId() == null) {
            dept.setParentId(0);
        }
        deptService.updateByPrimaryKey(dept);
        return ResultBean.success();
    }
}
