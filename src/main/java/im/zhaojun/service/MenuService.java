package im.zhaojun.service;

import im.zhaojun.mapper.MenuMapper;
import im.zhaojun.model.Menu;
import im.zhaojun.model.User;
import im.zhaojun.model.vo.MenuTreeVO;
import im.zhaojun.util.MenuVOConvert;
import im.zhaojun.util.TreeUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;

    /**
     * 获取所有菜单(导航菜单和按钮)
     */
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
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
        List<MenuTreeVO> menuTreeVOS = MenuVOConvert.menuToTreeVO(menus);
        return TreeUtil.toTree(menuTreeVOS);
    }

    /**
     * 获取当前登陆用户拥有的树形菜单
     */
    public List<MenuTreeVO> selectCurrentUserMenuTreeVO() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Menu> menus = selectMenuByUserName(user.getUsername());
        List<MenuTreeVO> menuTreeVOS = MenuVOConvert.menuToTreeVO(menus);
        return TreeUtil.toTree(menuTreeVOS);
    }

    /**
     * 根据用户名获取所拥有的树形菜单
     * @param userName
     * @return
     */
    private List<Menu> selectMenuByUserName(String userName) {
        return menuMapper.selectMenuByUserName(userName);
    }

    public int add(Menu menu) {
        menuMapper.insert(menu);
        return menu.getMenuId();
    }

    public boolean update(Menu menu) {
        return menuMapper.updateByPrimaryKey(menu) == 1;
    }

    public boolean deleteByParentId(Integer parentId) {
        menuMapper.deleteByParentId(parentId);
        return true;
    }

    public boolean delete(Integer id) {
        return menuMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 删除当前菜单以及其子菜单
     */
    public boolean deleteByIDAndChildren(Integer id) {
        List<Integer> childIDList = menuMapper.selectChildrenID(id);
        for (Integer childID : childIDList) {
            deleteByIDAndChildren(childID);
        }
        return delete(id);
    }

    /**
     * 从数据库加载权限列表
     */
    public Map<String, String> getUrlPermsMap() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");

        List<Menu> menus = selectAll();
        for (Menu menu : menus) {
            filterChainDefinitionMap.put("/" + menu.getUrl(), "perms[" + menu.getPerms() + "]");
        }

        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }
}
