package im.zhaojun.mapper;

import im.zhaojun.model.Menu;
import im.zhaojun.model.vo.RoleMenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 获取所有菜单(导航菜单和按钮)
     */
    List<Menu> selectAll();

    /**
     * 获取所有导航菜单
     */
    List<Menu> selectAllMenu();

    /**
     * 删除当前菜单的所有子菜单
     */
    int deleteByParentId(Integer parentId);

    /**
     * 查找某菜单的所有子类 ID
     */
    List<Integer> selectChildrenID(Integer id);

    /**
     * 获取某个用户的所拥有的导航菜单
     */
    List<Menu> selectMenuByUserName(@Param("userName") String userName);

    /**
     * 根据角色获取所有的权限ID
     */
    List<Integer> selectMenuIdByRoleId(@Param("roleId") Integer roleId);

    int count();

    List<RoleMenuVO> selectAllRoleByMenuId(@Param("menuId") Integer menuId);
}