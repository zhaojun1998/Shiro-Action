package im.zhaojun.controller;

import com.github.pagehelper.PageInfo;
import im.zhaojun.annotation.OperationLog;
import im.zhaojun.model.SysLog;
import im.zhaojun.service.SysLogService;
import im.zhaojun.util.PageResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SysLogController {

    @Resource
    private SysLogService sysLogService;

    @GetMapping("/log/sys/index")
    public String index() {
        return "log/sys-logs";
    }

    @OperationLog("查看操作日志")
    @GetMapping("/log/sys/list")
    @ResponseBody
    public PageResultBean<SysLog> getList(@RequestParam(value = "page", defaultValue = "1") int page,
                                          @RequestParam(value = "limit", defaultValue = "10")int limit) {
        List<SysLog> loginLogs = sysLogService.selectAll(page, limit);
        PageInfo<SysLog> rolePageInfo = new PageInfo<>(loginLogs);
        return new PageResultBean<>(rolePageInfo.getTotal(), rolePageInfo.getList());
    }

}
