package im.zhaojun.service;

import com.github.pagehelper.PageHelper;
import im.zhaojun.mapper.RoleMapper;
import im.zhaojun.model.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    public List<Role> findListByName(String name, int page, int rows) {
        PageHelper.startPage(page, rows);
        return roleMapper.findListByName(name);
    }
}
