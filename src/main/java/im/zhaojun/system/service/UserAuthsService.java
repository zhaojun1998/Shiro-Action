package im.zhaojun.system.service;

import im.zhaojun.common.constants.AuthcTypeEnum;
import im.zhaojun.system.mapper.UserAuthsMapper;
import im.zhaojun.system.model.UserAuths;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserAuthsService {

    @Resource
    private UserAuthsMapper userAuthsMapper;

    public int deleteByPrimaryKey(Integer id) {
        return userAuthsMapper.deleteByPrimaryKey(id);
    }

    public int insert(UserAuths record) {
        return userAuthsMapper.insert(record);
    }

    public int insertSelective(UserAuths record) {
        return userAuthsMapper.insertSelective(record);
    }

    public UserAuths selectByPrimaryKey(Integer id) {
        return userAuthsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserAuths record) {
        return userAuthsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserAuths record) {
        return userAuthsMapper.updateByPrimaryKey(record);
    }

	public UserAuths selectOneByIdentityTypeAndUserId(AuthcTypeEnum authcTypeEnum, Integer userId){
		 return userAuthsMapper.selectOneByIdentityTypeAndUserId(authcTypeEnum.getDescription(), userId);
	}

    public UserAuths selectOneByIdentityTypeAndIdentifier(AuthcTypeEnum authcTypeEnum, String identifier){
        return userAuthsMapper.selectOneByIdentityTypeAndIdentifier(authcTypeEnum.getDescription(), identifier);
    }
    public List<UserAuths> selectOneByIdentifier(String identifier){
        return userAuthsMapper.selectOneByIdentifier(identifier);
    }

    public int deleteByUserId(Integer userId) {
        return userAuthsMapper.deleteByUserId(userId);
    }
}

