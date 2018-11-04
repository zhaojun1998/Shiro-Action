package im.zhaojun.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import im.zhaojun.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMenuMapper<insertList> {
    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    int insertList(@Param("roleId") Integer roleId, @Param("menuIds") Integer[] menuIds);

    int deleteRoleMenuByRoleId(@Param("roleId") Integer roleId);
}