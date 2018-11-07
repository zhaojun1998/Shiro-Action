package im.zhaojun.util;

import im.zhaojun.model.Menu;
import im.zhaojun.model.vo.MenuTreeVO;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class MenuVOConvert {

    public static List<MenuTreeVO> menuToTree(List<Menu> menus) {
        Map<Integer, MenuTreeVO> result = new HashMap<>();
        Map<Integer, MenuTreeVO> hash = new HashMap<>();
        for (Menu menu : menus) {
            hash.put(menu.getMenuId(), menuToTreeVO(menu));
        }

        for (Menu menu : menus) {
            MenuTreeVO menuTreeVO = menuToTreeVO(menu);

            Integer parentId = menu.getParentId();

            MenuTreeVO parent = result.containsKey(parentId) ? result.get(parentId): hash.get(parentId);

            if (parent != null) {
                List<MenuTreeVO> children = parent.getChildren();
                if (children == null) {
                    List<MenuTreeVO> newChildren = new ArrayList<>();
                    newChildren.add(menuTreeVO);
                    parent.setChildren(newChildren);
                } else {
                    children.add(menuTreeVO);
                    parent.setChildren(children);
                }
                result.put(parent.getMenuId(), parent);
            } else {
                result.put(menuTreeVO.getMenuId(), menuTreeVO);
            }
        }

        ArrayList<MenuTreeVO> list = new ArrayList<>();
        Set<Map.Entry<Integer, MenuTreeVO>> entries = result.entrySet();
        for (Map.Entry<Integer, MenuTreeVO> entry : entries) {
            list.add(entry.getValue());
        }
        return list;
    }

    public static MenuTreeVO menuToTreeVO(Menu menu) {
        MenuTreeVO menuTreeVO = new MenuTreeVO();
        BeanUtils.copyProperties(menu, menuTreeVO);
        return menuTreeVO;
    }


    private static Map<Integer, MenuTreeVO> result = new HashMap<>();
    private static Map<Integer, Menu> hash = new HashMap<>();

    public static List<MenuTreeVO> menuToTree2(List<Menu> menus) {
        for (Menu menu : menus) {
            hash.put(menu.getMenuId(), menu);
        }

        for (Menu menu : menus) {
//            MenuTreeVO menuTreeVO = menuToTreeVO(menu);
            getChildren(menu);
        }

        ArrayList<MenuTreeVO> list = new ArrayList<>();
        Set<Map.Entry<Integer, MenuTreeVO>> entries = result.entrySet();
        for (Map.Entry<Integer, MenuTreeVO> entry : entries) {
            list.add(entry.getValue());
        }
        return list;
    }

    private static List<Menu> getChildren(Menu menu) {

        List<Menu> list = new ArrayList<>();

        for (Map.Entry<Integer, Menu> menuTreeVOEntry : hash.entrySet()) {
            getChildren(menuTreeVOEntry.getValue());
            Menu value = menuTreeVOEntry.getValue();
            if (value.getParentId().equals(menu.getMenuId())) {
                list.add(value);
                hash.remove(menuTreeVOEntry.getKey());
            }
        }
        return list;
    }



    /**
     *方法三
     * @param list
     * @return
     */
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
}
