package im.zhaojun.service;

import com.github.pagehelper.PageHelper;
import im.zhaojun.mapper.MenuMapper;
import im.zhaojun.mapper.RoleMapper;
import im.zhaojun.mapper.RoleMenuMapper;
import im.zhaojun.model.Role;
import im.zhaojun.shiro.realm.UserNameRealm;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "role", keyGenerator = "springCacheKeyGenerator")
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

    @Cacheable
    public List<Role> selectAll(int page, int limit) {
        PageHelper.startPage(page, limit);
        return selectAll();
    }

    @Cacheable
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @CacheEvict(allEntries = true)
    public int add(Role role, Integer[] menuIds) {
        roleMapper.insert(role);
        roleMenuMapper.insertMenusWithRole(role.getRoleId(), menuIds);
        return role.getRoleId();
    }

    @CacheEvict(allEntries = true)
    public int update(Role role, Integer[] menuIds) {
        roleMapper.updateByPrimaryKey(role);
        roleMenuMapper.deleteByRoleId(role.getRoleId());
        roleMenuMapper.insertMenusWithRole(role.getRoleId(), menuIds);
        userNameRealm.clearAuthorizationCache();
        return role.getRoleId();
    }

    public List<Integer> selectMenuIdByRoleId(Integer roleId) {
        return menuMapper.selectMenuIdByRoleId(roleId);
    }

    public int count() {
        return roleMapper.count();
    }
}