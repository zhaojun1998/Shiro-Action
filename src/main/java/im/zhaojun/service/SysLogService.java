package im.zhaojun.service;

import com.github.pagehelper.PageHelper;
import im.zhaojun.mapper.SysLogMapper;
import im.zhaojun.model.SysLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    public List<SysLog> selectAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        return sysLogMapper.selectAll();
    }
}
