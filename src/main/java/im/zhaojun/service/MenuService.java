package im.zhaojun.service;

import im.zhaojun.mapper.MenuMapper;
import im.zhaojun.mapper.RoleMenuMapper;
import im.zhaojun.model.Menu;
import im.zhaojun.model.User;
import im.zhaojun.model.vo.MenuTreeVO;
import im.zhaojun.model.vo.RoleMenuVO;
import im.zhaojun.util.TreeUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    /**
     * 获取所有菜单(导航菜单和按钮)
     */
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    /**
     * 获取所有菜单(导航菜单和按钮)
     */
    public List<Menu> selectByParentId(Integer parentId) {
        return menuMapper.selectByParentId(parentId);
    }

    /**
     * 获取所有导航菜单
     */
    public List<Menu> selectAllMenu() {
        return menuMapper.selectAllMenu();
    }

    public Menu selectOne(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取所有菜单 (树形结构)
     */
    public List<MenuTreeVO> getALLMenuTreeVO() {
        List<Menu> menus = selectAllMenu();
        List<MenuTreeVO> menuTreeVOS = TreeUtil.toTree(menus);
        MenuTreeVO root = new MenuTreeVO();
        root.setMenuId(0);
        root.setMenuName("导航目录");
        root.setChildren(menuTreeVOS);
        List<MenuTreeVO> rootList = new ArrayList<>();
        rootList.add(root);
        return rootList;
    }

    /**
     * 获取当前登陆用户拥有的树形菜单 (admin 账户拥有所有权限.)
     */
    public List<MenuTreeVO> selectCurrentUserMenuTreeVO() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Menu> menus;
        if ("admin".equals(user.getUsername())) {
            menus = menuMapper.selectAllMenu();
        } else {
            menus = menuMapper.selectMenuByUserName(user.getUsername());
        }
        return TreeUtil.toTree(menus);
    }

    public int add(Menu menu) {
        menuMapper.insert(menu);
        return menu.getMenuId();
    }

    public boolean update(Menu menu) {
        return menuMapper.updateByPrimaryKey(menu) == 1;
    }


    /**
     * 删除当前菜单以及其子菜单
     */
    @Transactional
    public boolean deleteByIDAndChildren(Integer id) {
        List<Integer> childIDList = menuMapper.selectChildrenID(id);
        for (Integer childID : childIDList) {
            deleteByIDAndChildren(childID);
        }
        return menuMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 从数据库加载权限列表
     */
    public Map<String, String> getUrlPermsMap() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 系统默认过滤器
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        // 验证码
        filterChainDefinitionMap.put("/captcha", "anon");
        // 检查用户名是否存在
        filterChainDefinitionMap.put("/checkUser", "anon");
        List<Menu> menus = selectAll();
        for (Menu menu : menus) {
            String url = menu.getUrl();
            if (url != null) {
//                if (menu.getMethod() != null && !"".equals(menu.getMethod())) {
//                    url += ("==" + menu.getMethod());
//                }
                String perms = "perms[" + menu.getPerms() + "]";
                filterChainDefinitionMap.put(url, perms);
            }
        }

        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

    public int count() {
        return menuMapper.count();
    }


    public List<RoleMenuVO> selectAllRoleByMenuId(Integer menuId) {
        return menuMapper.selectAllRoleByMenuId(menuId);
    }

    @Transactional
    public void allocationRole(Integer menuId, Integer[] roleIds) {
        roleMenuMapper.deleteByMenuId(menuId);
        roleMenuMapper.insertRolesWithMenu(menuId, roleIds);
    }
}