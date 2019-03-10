package im.zhaojun.util;

import im.zhaojun.model.Menu;
import im.zhaojun.model.vo.MenuTreeVO;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeUtil {

    /**
     * 所有待用"菜单"
     */
    private static List<MenuTreeVO> all = null;

    /**
     * 转换为树形
     * @param menus 所有节点
     * @return 转换后的树结构菜单
     */
    public static List<MenuTreeVO> toTree(List<Menu> menus) {
        List<MenuTreeVO> list = MenuVOConvert.menuToTreeVO(menus);
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        // 最初, 所有的 "菜单" 都是待用的
        all = new ArrayList<>(list);

        // 拿到所有的顶级 "菜单"
        List<MenuTreeVO> roots = new ArrayList<>();

        for (MenuTreeVO menuTreeVO : list) {
            if (menuTreeVO.getParentId() == 0) {
                roots.add(menuTreeVO);
            }
        }

        // 将所有顶级菜单从 "待用菜单列表" 中删除
        all.removeAll(roots);

        for (MenuTreeVO menuTreeVO : roots) {
            menuTreeVO.setChildren(getCurrentNodeChildren(menuTreeVO));;
        }
        return roots;
    }

    /**
     * 递归函数
     *      递归目的: 拿到子节点
     *      递归终止条件: 没有子节点
     * @param parent 父节点
     * @return  子节点
     */
    private static List<MenuTreeVO> getCurrentNodeChildren(MenuTreeVO parent) {
        // 判断当前节点有没有子节点, 没有则创建一个空长度的 List, 有就使用之前已有的所有子节点.
        List<MenuTreeVO> childList = parent.getChildren() == null ? new ArrayList<>() : parent.getChildren();

        // 从 "待用菜单列表" 中找到当前节点的所有子节点
        for (MenuTreeVO child : all) {
            if (parent.getMenuId().equals(child.getParentId())) {
                childList.add(child);
            }
        }

        // 将当前节点的所有子节点从 "待用菜单列表" 中删除
        all.removeAll(childList);

        // 所有的子节点再寻找它们自己的子节点
        for (MenuTreeVO menuTreeVO : childList) {
            menuTreeVO.setChildren(getCurrentNodeChildren(menuTreeVO));
        }
        return childList;
    }


    public static List<MenuTreeVO> menuListToMenuTree(List<Menu> menus) {
        return TreeUtil.toTree(menus);
    }

    /**
     * 获取导航菜单中的所有叶子节点
     */
    public static List<MenuTreeVO> getLeafNodeMenuByMenuTreeVO(List<MenuTreeVO> menuTreeVOList) {
        List<MenuTreeVO> menuList = new ArrayList<>();

        Queue<MenuTreeVO> queue = new ArrayDeque<>();
        for (MenuTreeVO menuTreeVO : menuTreeVOList) {
            if (menuTreeVO.getChildren().isEmpty()) {
                menuList.add(menuTreeVO);
            } else {
                queue.addAll(menuTreeVO.getChildren());
            }
        }

        while (!queue.isEmpty()) {
            MenuTreeVO menuTreeVO = queue.poll();
            if (menuTreeVO.getChildren().isEmpty()) {
                menuList.add(menuTreeVO);
            } else {
                queue.addAll(menuTreeVO.getChildren());
            }
        }
        return menuList;
    }

}