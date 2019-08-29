package im.zhaojun.system.service;

import com.github.pagehelper.PageHelper;
import im.zhaojun.common.shiro.realm.UserNameRealm;
import im.zhaojun.system.mapper.RoleMapper;
import im.zhaojun.system.mapper.RoleMenuMapper;
import im.zhaojun.system.mapper.RoleOperatorMapper;
import im.zhaojun.system.mapper.UserRoleMapper;
import im.zhaojun.system.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private UserNameRealm userNameRealm;

    @Resource
    private RoleOperatorMapper roleOperatorMapper;

    public Role selectOne(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    public List<Role> selectAll(int page, int limit, Role roleQuery) {
        PageHelper.startPage(page, limit);
        return selectAllByQuery(roleQuery);
    }

    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    public List<Role> selectAllByQuery(Role roleQuery) {
        return roleMapper.selectAllByQuery(roleQuery);
    }

    @Transactional
    public void add(Role role) {
        roleMapper.insert(role);
    }

    @Transactional
    public void update(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }


    /**
     * 为角色分配菜单
     * @param roleId    角色 ID
     * @param menuIds   菜单 ID 数组
     */
    @Transactional
    public void grantMenu(Integer roleId, Integer[] menuIds) {
        roleMenuMapper.deleteByRoleId(roleId);
        if (menuIds != null && menuIds.length != 0) {
            roleMenuMapper.insertRoleMenus(roleId, menuIds);
        }
        clearRoleAuthCache(roleId);
    }

    /**
     * 为角色分配操作权限
     * @param roleId    角色 ID
     * @param operatorIds   操作权限 ID 数组
     */
    @Transactional
    public void grantOperator(Integer roleId, Integer[] operatorIds) {
        roleOperatorMapper.deleteByRoleId(roleId);
        if (operatorIds != null && operatorIds.length != 0) {
            for (int i = 0; i < operatorIds.length; i++) {
                operatorIds[i] = operatorIds[i] - 10000;
            }
            roleOperatorMapper.insertRoleOperators(roleId, operatorIds);
        }
        clearRoleAuthCache(roleId);
    }

    public int count() {
        return roleMapper.count();
    }

    @Transactional
    public void delete(Integer roleId) {
        userRoleMapper.deleteUserRoleByRoleId(roleId);
        roleMapper.deleteByPrimaryKey(roleId);
        roleMenuMapper.deleteByRoleId(roleId);
        roleOperatorMapper.deleteByRoleId(roleId);
    }

    public Integer[] getMenusByRoleId(Integer roleId) {
        return roleMenuMapper.getMenusByRoleId(roleId);
    }

    public Integer[] getOperatorsByRoleId(Integer roleId) {
        return roleOperatorMapper.getOperatorsByRoleId(roleId);
    }

    private void clearRoleAuthCache(Integer roleId) {
        // 获取该角色下的所有用户.
        List<Integer> userIds = userRoleMapper.selectUserIdByRoleId(roleId);

        // 将该角色下所有用户的认证信息缓存清空, 以到达刷新认证信息的目的.
        for (Integer userId : userIds) {
            userNameRealm.clearAuthCacheByUserId(userId);
        }
    }
}