package im.zhaojun.mapper;

import im.zhaojun.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMenuMapper<insertList> {
    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    /**
     * 插入多条 角色-菜单 关联关系
     */
    int insertRoleMenus(@Param("roleId") Integer roleId, @Param("menuIds") Integer[] menuIds);

    /**
     * 清空角色所拥有的所有菜单
     */
    int deleteByRoleId(@Param("roleId") Integer roleId);

    /**
     * 取消某个菜单的所有授权用户
     */
    int deleteByMenuId(@Param("menuId") Integer menuId);

    Integer[] getMenusByRoleId(@Param("roleId")Integer roleId);
}