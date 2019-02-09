package im.zhaojun.mapper;

import im.zhaojun.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);


    /**
     * 插入多条 用户色-角色 关联关系
     */
    int insertList(@Param("userId") Integer userId, @Param("roleIds") Integer[] roleIds);

    /**
     * 清空用户所拥有的所有角色
     */
    int deleteUserMenuByUserId(@Param("userId") Integer userId);

    /**
     * 清空此角色与所有角色的关联关系
     */
    int deleteUserMenuByRoleId(@Param("roleId") Integer roleId);

}