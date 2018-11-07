package im.zhaojun.util;

import im.zhaojun.model.vo.MenuTreeVO;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    private static List<MenuTreeVO> all = null;

    /**
     * 拿到所有顶级菜单
     * 遍历每一个顶级菜单获得其子节点
     * @param list 所有节点
     * @return 转换后的树结构菜单
     */
    public static List<MenuTreeVO> toTree(List<MenuTreeVO> list) {
        all = new ArrayList<>(list);
        List<MenuTreeVO> root = new ArrayList<MenuTreeVO>();

        for (MenuTreeVO menuTreeVO : list) {
            if (menuTreeVO.getParentId() == 0) {
                root.add(menuTreeVO);
            }
        }

        all.removeAll(root);

        for (MenuTreeVO menuTreeVO : root) {
            menuTreeVO.setChildren(getChildren(menuTreeVO));;
        }
        return root;
    }

    /**
     * 递归函数
     *      递归目的: 拿到子节点
     *      递归终止条件: 没有子节点
     * @param parent 父节点
     * @return  子节点
     */
    private static List<MenuTreeVO> getChildren(MenuTreeVO parent) {
        List<MenuTreeVO> childList = parent.getChildren() == null ? new ArrayList<>() : parent.getChildren();
        for (MenuTreeVO child : all) {
            if (parent.getMenuId().equals(child.getParentId())) {
                childList.add(child);
            }
        }

        all.removeAll(childList);

        for (MenuTreeVO menuTreeVO : childList) {
            menuTreeVO.setChildren(getChildren(menuTreeVO));
        }
        return childList;
    }
}
