package im.zhaojun.system.mapper;

import im.zhaojun.system.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User user);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);


    /**
     * 获取用户所拥有的所有权限
     */
    Set<String> selectPermsByUserName(@Param("username") String username);

    /**
     * 获取用户所拥有的所有角色
     */
    Set<String> selectRoleNameByUserName(@Param("username") String username);

    /**
     * 根据用户名获取用户
     */
    User selectOneByUserName(@Param("username") String username);

    /**
     * 获取所有用户
     */
    List<User> selectAll();

    /**
     * 获取所有用户
     */
    List<User> selectAllWithDept(User userQuery);

    /**
     * 更改用户的状态为某项值
     */
    int updateStatusByPrimaryKey(@Param("id") Integer id, @Param("status") int status);

    /**
     * 更新用户最后登录事件
     */
    int updateLastLoginTimeByUsername(@Param("username") String username);

    /**
     * 统计已经有几个此用户名, 用来检测用户名是否重复.
     */
    int countByUserName(@Param("username") String username);

    /**
     * 统计已经有几个此用户名, 用来检测用户名是否重复 (不包含某用户 ID).
     */
    int countByUserNameNotIncludeUserId(@Param("username") String username, @Param("userId") Integer userId);

    /**
     * 查询此用户拥有的所有角色的 ID
     *
     * @param userId 用户 ID
     * @return 拥有的角色 ID 数组
     */
    Integer[] selectRoleIdsByUserId(@Param("userId") Integer userId);

    /**
     * 根据邮箱激活码, 查询要被激活的用户.
     */
    User selectByActiveCode(@Param("activeCode") String activeCode);

    /**
     * 统计系统中有多少个用户.
     */
    int count();

    /**
     * 获取用户所拥有的操作权限
     */
    Set<String> selectOperatorPermsByUserName(@Param("username") String username);

    int updatePasswordByUserId(@Param("userId") Integer userId, @Param("password") String password, @Param("salt") String salt);

    int activeUserByUserId(Integer userId);

//    selectAllByUsernameLikeAndStatus

}