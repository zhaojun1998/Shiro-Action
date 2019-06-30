package im.zhaojun.system.mapper;

import im.zhaojun.system.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu menu);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKey(Menu menu);

    /**
     * 获取所有菜单
     */
    List<Menu> selectAll();

    List<Menu> selectAllTree();

    List<Menu> selectAllMenuAndCountOperator();

    List<Menu> selectByParentId(Integer parentId);

    /**
     * 删除当前菜单的所有子菜单
     */
    int deleteByParentId(Integer parentId);

    /**
     * 查找某菜单的所有子类 ID
     */
    List<Integer> selectChildrenIDByPrimaryKey(@Param("menuId") Integer menuId);

    /**
     * 获取某个用户的所拥有的导航菜单
     */
    List<Menu> selectMenuByUserName(@Param("userName") String userName);

    int count();

    /**
     * 交换两个菜单的顺序
     */
    int swapSort(@Param("currentId") Integer currentId, @Param("swapId") Integer swapId);

    int selectMaxOrderNum();

}