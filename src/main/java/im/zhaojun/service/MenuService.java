package im.zhaojun.service;

import im.zhaojun.mapper.MenuMapper;
import im.zhaojun.model.Menu;
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

    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    public List<Menu> selectAllMenu() {
        return menuMapper.selectAllMenu();
    }

    public Menu selectOne(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    public List<MenuTreeVO> getALLMenuTreeVO() {
        List<Menu> menus = selectAllMenu();
        List<MenuTreeVO> menuTreeVOS = MenuVOConvert.menuToTreeVO(menus);
        return TreeUtil.toTree(menuTreeVOS);
    }

    public List<MenuTreeVO> getCurrentUserMenuTreeVO() {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        List<Menu> menus = selectMenuByUserName(userName);
        List<MenuTreeVO> menuTreeVOS = MenuVOConvert.menuToTreeVO(menus);
        return TreeUtil.toTree(menuTreeVOS);
    }


    public List<Menu> selectMenuByUserName(String userName) {
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
    public Map<String, String> loadFilterChainDefinitions() {
        // 权限控制map.从数据库获取
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
