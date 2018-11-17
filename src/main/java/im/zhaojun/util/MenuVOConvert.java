package im.zhaojun.util;

import im.zhaojun.model.Menu;
import im.zhaojun.model.vo.MenuTreeVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuVOConvert {

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