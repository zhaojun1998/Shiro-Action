package im.zhaojun.service;

import im.zhaojun.mapper.MenuMapper;
import im.zhaojun.model.Menu;
import im.zhaojun.model.vo.MenuTreeVO;
import im.zhaojun.util.MenuVOConvert;
import im.zhaojun.util.TreeUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        List<Menu> menus = selectMenuByUserId(userName);
        List<MenuTreeVO> menuTreeVOS = MenuVOConvert.menuToTreeVO(menus);
        return TreeUtil.toTree(menuTreeVOS);
    }


    public List<Menu> selectMenuByUserId(String userName) {
        return menuMapper.selectMenuByUserId(userName);
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
}
