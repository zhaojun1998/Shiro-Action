package im.zhaojun.service;

import im.zhaojun.mapper.MenuMapper;
import im.zhaojun.model.vo.MenuTreeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;

    public List<MenuTreeVO> getAllMenuTreeVO() {
        return menuMapper.selectAllMenuTreeVO();
    }

    public List<MenuTreeVO> getMenuTreeVO() {
        return menuMapper.selectMenuTreeVO();
    }
}
