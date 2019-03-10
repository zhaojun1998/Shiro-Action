package im.zhaojun.service;

import im.zhaojun.mapper.MenuMapper;
import im.zhaojun.mapper.OperatorMapper;
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
import java.util.List;

@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private OperatorMapper operatorMapper;

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
        return menuMapper.selectAll();
    }

    public Menu selectOne(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取所有菜单 (树形结构)
     */
    public List<MenuTreeVO> getALLMenuTreeVO() {
        List<Menu> menus = selectAllMenu();
        for (Menu menu : menus) {
            menu.setCheckArr("0");
        }
        return TreeUtil.menuListToMenuTree(menus);
    }

    /**
     * 获取所有菜单并添加一个根节点 (树形结构)
     */
    public List<MenuTreeVO> getALLMenuTreeVOAndRoot() {
        List<MenuTreeVO> allMenuTreeVO = getALLMenuTreeVO();
        MenuTreeVO root = new MenuTreeVO();
        root.setMenuId(0);
        root.setMenuName("导航目录");
        root.setChildren(allMenuTreeVO);
        List<MenuTreeVO> rootList = new ArrayList<>();
        rootList.add(root);
        return rootList;
    }

    /**
     * 获取所有菜单并统计菜单下的操作权限数 (树形结构)
     */
    public List<MenuTreeVO> getALLMenuAndCountOperatorTreeVO() {
        List<Menu> menus = menuMapper.selectAllMenuAndCountOperator();
        return TreeUtil.menuListToMenuTree(menus);
    }

    /**
     * 获取当前登陆用户拥有的树形菜单 (admin 账户拥有所有权限.)
     */
    public List<MenuTreeVO> selectCurrentUserMenuTreeVO() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Menu> menus;
        if ("admin".equals(user.getUsername())) {
            menus = menuMapper.selectAll();
        } else {
            menus = menuMapper.selectMenuByUserName(user.getUsername());
        }
        return TreeUtil.toTree(menus);
    }

    /**
     * 获取指定用户拥有的树形菜单 (admin 账户拥有所有权限.)
     */
    public List<MenuTreeVO> selectMenuTreeVOByUsername(String username) {
        List<Menu> menus;
        if ("admin".equals(username)) {
            menus = menuMapper.selectAll();
        } else {
            menus = menuMapper.selectMenuByUserName(username);
        }
        return TreeUtil.toTree(menus);
    }

    /**
     * 获取导航菜单中的所有叶子节点
     */
    public List<MenuTreeVO> getLeafNodeMenu() {
        List<MenuTreeVO> allMenuTreeVO = getALLMenuTreeVO();
        return TreeUtil.getLeafNodeMenuByMenuTreeVO(allMenuTreeVO);
    }

    public void add(Menu menu) {
        int maxOrderNum = menuMapper.selectMaxOrderNum();
        menu.setOrderNum(maxOrderNum + 1);
        menuMapper.insert(menu);
    }

    public void update(Menu menu) {
        menuMapper.updateByPrimaryKey(menu);
    }


    /**
     * 删除当前菜单以及其子菜单
     */
    @Transactional
    public void deleteByIDAndChildren(Integer menuId) {
        // 删除子菜单
        List<Integer> childIDList = menuMapper.selectChildrenID(menuId);
        for (Integer childID : childIDList) {
            deleteByIDAndChildren(childID);
        }
        // 删除子操作权限
        operatorMapper.deleteByMenuId(menuId);
        // 删除分配给用户的菜单
        roleMenuMapper.deleteByMenuId(menuId);
        // 删除自身
        menuMapper.deleteByPrimaryKey(menuId);
    }

    public int count() {
        return menuMapper.count();
    }


    public List<RoleMenuVO> selectAllRoleByMenuId(Integer menuId) {
        return menuMapper.selectAllRoleByMenuId(menuId);
    }

    public void swapSort(Integer currentId, Integer swapId) {
        menuMapper.swapSort(currentId, swapId);
    }
}