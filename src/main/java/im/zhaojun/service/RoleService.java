package im.zhaojun.service;

import com.github.pagehelper.PageHelper;
import im.zhaojun.mapper.MenuMapper;
import im.zhaojun.mapper.RoleMapper;
import im.zhaojun.mapper.RoleMenuMapper;
import im.zhaojun.model.Role;
import im.zhaojun.shiro.realm.UserNameRealm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private UserNameRealm userNameRealm;

    public Role selectOne(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    public List<Role> selectAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        return roleMapper.selectAll();
    }

    public int add(Role role, Integer[] menuIds) {
        roleMapper.insert(role);
        roleMenuMapper.insertList(role.getRoleId(), menuIds);
        return role.getRoleId();
    }

    public int update(Role role, Integer[] menuIds) {
        roleMapper.updateByPrimaryKey(role);
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        roleMenuMapper.insertList(role.getRoleId(), menuIds);
        userNameRealm.clearAuthorizationCache();
        return role.getRoleId();
    }

    public List<Integer> selectMenuIdByRoleId(Integer roleId) {
        return menuMapper.selectMenuIdByRoleId(roleId);
    }
}
