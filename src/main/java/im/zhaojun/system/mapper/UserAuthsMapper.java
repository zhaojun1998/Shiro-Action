package im.zhaojun.system.mapper;

import im.zhaojun.system.model.UserAuths;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAuthsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuths record);

    UserAuths selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(UserAuths record);

    UserAuths selectOneByIdentityTypeAndUserId(@Param("identityType") String identityType, @Param("userId") Integer userId);

    List<UserAuths> selectOneByIdentifier(@Param("identifier") String identifier);

    int deleteByUserId(@Param("userId") Integer userId);

    UserAuths selectOneByIdentityTypeAndIdentifier(@Param("identityType") String identityType, @Param("identifier") String identifier);
}