package im.zhaojun.util;

import im.zhaojun.model.Menu;
import im.zhaojun.model.vo.MenuTreeVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MenuVOConvert {

    public static List<MenuTreeVO> menuToTreeVO(List<Menu> menus) {
        List<MenuTreeVO> menuTreeVOS = new ArrayList<>();
        for (Menu menu : menus) {
            menuTreeVOS.add(menuToTreeVO(menu));
        }
        return menuTreeVOS;
    }

    public static MenuTreeVO menuToTreeVO(Menu menu) {
        MenuTreeVO menuTreeVO = new MenuTreeVO();
        BeanUtils.copyProperties(menu, menuTreeVO);
        return menuTreeVO;
    }

    public static Menu menuTreeVoToMenu(MenuTreeVO menuTreeVO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuTreeVO, menu);
        return menu;
    }


    /**
     * 获取导航菜单中的所有叶子节点
     */
    public static List<Menu> getLeafNodeMenuByMenuTreeVO(List<MenuTreeVO> menuTreeVOList) {
        List<Menu> menuList = new ArrayList<>();

        Queue<MenuTreeVO> queue = new ArrayDeque<>();
        for (MenuTreeVO menuTreeVO : menuTreeVOList) {
            if (menuTreeVO.getChildren().isEmpty()) {
                menuList.add(MenuVOConvert.menuTreeVoToMenu(menuTreeVO));
            } else {
                queue.addAll(menuTreeVO.getChildren());
            }
        }

        while (!queue.isEmpty()) {
            MenuTreeVO menuTreeVO = queue.poll();
            if (menuTreeVO.getChildren().isEmpty()) {
                menuList.add(MenuVOConvert.menuTreeVoToMenu(menuTreeVO));
            } else {
                queue.addAll(menuTreeVO.getChildren());
            }
        }
        return menuList;
    }
}