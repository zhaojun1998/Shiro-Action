package im.zhaojun.mapper;

import im.zhaojun.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Set<String> findPermsByUserName(@Param("username")String username);

    Set<String> findRoleNameByUserName(@Param("username")String username);

    User findOneByUserName(@Param("username")String username);
}