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
    int insertList(@Param("roleId") Integer roleId, @Param("menuIds") Integer[] menuIds);

    /**
     * 清空角色所拥有的所有菜单
     */
    int deleteRoleMenuByRoleId(@Param("roleId") Integer roleId);
}