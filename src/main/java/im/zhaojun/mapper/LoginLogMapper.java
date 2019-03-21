package im.zhaojun.mapper;

import im.zhaojun.model.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginLog loginLog);

    int insertSelective(LoginLog loginLog);

    LoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLog loginLog);

    int updateByPrimaryKey(LoginLog loginLog);

    List<LoginLog> selectAll();

    List<Integer> recentlyWeekLoginCount(@Param("username") String username);

    int count();
}