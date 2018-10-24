package im.zhaojun.mapper;

import im.zhaojun.model.Menu;
import im.zhaojun.model.vo.MenuTreeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<MenuTreeVO> selectAllMenuTreeVO();
}