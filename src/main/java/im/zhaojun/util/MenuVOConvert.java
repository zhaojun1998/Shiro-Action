package im.zhaojun.util;

import im.zhaojun.model.Menu;
import im.zhaojun.model.vo.MenuTreeVO;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class MenuVOConvert {

    private static Map<Integer, MenuTreeVO> result = new HashMap<>();
    private static Map<Integer, Menu> hash = new HashMap<>();

    public static List<MenuTreeVO> toTree(List<MenuTreeVO> list) {
        List<MenuTreeVO> treeList = new ArrayList<>();
        for (MenuTreeVO tree : list) {
            if(tree.getParentId() == 0){
                treeList.add(tree);
            }
        }
        for (MenuTreeVO tree : list) {
            toTreeChildren(treeList, tree);
        }
        return treeList;
    }

    private static void toTreeChildren(List<MenuTreeVO> treeList, MenuTreeVO tree) {
        for (MenuTreeVO node : treeList) {
            if(node.getMenuId().equals(tree.getParentId())){
                if(node.getChildren() == null){
                    node.setChildren(new ArrayList<>());
                }
                node.getChildren().add(tree);
            }
            if(node.getChildren() != null){
                toTreeChildren(node.getChildren(),tree);
            }
        }
    }

    /**
     * 查找当前菜单的所有子菜单, 并设置到当前菜单对象的 children 字段中.
     */
    private static void setChildrenBySelf(Menu menu) {
        List<Menu> list = new ArrayList<>();

        for (Map.Entry<Integer, Menu> menuTreeVOEntry : hash.entrySet()) {
            setChildrenBySelf(menuTreeVOEntry.getValue());
            Menu value = menuTreeVOEntry.getValue();
            if (value.getParentId().equals(menu.getMenuId())) {
                list.add(value);
                hash.remove(menuTreeVOEntry.getKey());
            }
        }
    }

    public static List<MenuTreeVO> menuToTreeVO(List<Menu> menus) {
        List<MenuTreeVO> menuTreeVOS = new ArrayList<>();
        for (Menu menu : menus) {
            menuTreeVOS.add(menuToTreeVO(menu));
        }
        return menuTreeVOS;
    }

    private static MenuTreeVO menuToTreeVO(Menu menu) {
        MenuTreeVO menuTreeVO = new MenuTreeVO();
        BeanUtils.copyProperties(menu, menuTreeVO);
        return menuTreeVO;
    }
}
