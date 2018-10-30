package im.zhaojun.service;

import im.zhaojun.mapper.MenuMapper;
import im.zhaojun.model.Menu;
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


    public int add(Menu menu) {
        menuMapper.insert(menu);
        return menu.getMenuId();
    }

    public boolean deleteByParentId(Integer parentId) {
        menuMapper.deleteByParentId(parentId);
        return true;
    }

    public boolean delete(Integer id) {
        return menuMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean deleteByIDAndChildren(Integer id) {
        List<Menu> menus = menuMapper.selectChildren(id);
        for (Menu menu : menus) {
            deleteByIDAndChildren(menu.getMenuId());
        }
        return delete(id);
    }
}
