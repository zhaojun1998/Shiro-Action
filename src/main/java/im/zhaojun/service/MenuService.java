package im.zhaojun.service;

import im.zhaojun.mapper.MenuMapper;
import im.zhaojun.mapper.RoleMenuMapper;
import im.zhaojun.model.Menu;
import im.zhaojun.model.User;
import im.zhaojun.model.vo.MenuTreeVO;
import im.zhaojun.model.vo.RoleMenuVO;
import im.zhaojun.util.MenuVOConvert;
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
        return menuListToMenuTree(menus);
    }

    /**
     * 获取所有菜单 (树形结构)
     */
    public List<MenuTreeVO> getALLMenuAndCountOperatorTreeVO() {
        List<Menu> menus = menuMapper.selectAllMenuAndCountOperator();
        return menuListToMenuTree(menus);
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
     * 获取导航菜单中的所有叶子节点
     */
    public List<Menu> getLeafNodeMenu() {
        List<MenuTreeVO> allMenuTreeVO = getALLMenuTreeVO();
        return MenuVOConvert.getLeafNodeMenuByMenuTreeVO(allMenuTreeVO);
    }

    public int add(Menu menu) {
        int maxOrderNum = menuMapper.selectMaxOrderNum();
        menu.setOrderNum(maxOrderNum + 1);
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

    public void swapSort(Integer currentId, Integer swapId) {
        menuMapper.swapSort(currentId, swapId);
    }

    private List<MenuTreeVO> menuListToMenuTree(List<Menu> menus) {
        List<MenuTreeVO> menuTreeVOS = TreeUtil.toTree(menus);
        MenuTreeVO root = new MenuTreeVO();
        root.setMenuId(0);
        root.setMenuName("导航目录");
        root.setChildren(menuTreeVOS);
        List<MenuTreeVO> rootList = new ArrayList<>();
        rootList.add(root);
        return rootList;
    }
}