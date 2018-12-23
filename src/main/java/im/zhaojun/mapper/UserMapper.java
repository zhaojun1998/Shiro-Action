package im.zhaojun.mapper;

import im.zhaojun.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper<findByActiveCode> {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 获取用户所拥有的所有权限
     */
    Set<String> selectPermsByUserName(@Param("username")String username);

    /**
     * 获取用户所拥有的所有角色
     */
    Set<String> selectRoleNameByUserName(@Param("username")String username);

    /**
     * 根据用户名获取用户
     */
    User selectOneByUserName(@Param("username")String username);

    /**
     * 获取所有用户
     */
    List<User> selectAll();

    /**
     * 更改用户的状态为某项值
     */
    int updateStatusByPrimaryKey(@Param("id") Integer id, @Param("status") int status);

    int updateLastLoginTimeByUsername(@Param("username") String username);

    int countByUserName(@Param("username") String username);

    Integer[] selectRoleIdsById(@Param("userId") Integer userId);

    User selectByActiveCode(@Param("activeCode") String activeCode);

    int count();
}