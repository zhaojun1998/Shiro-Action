package im.zhaojun.service;

import com.github.pagehelper.PageHelper;
import im.zhaojun.mapper.UserMapper;
import im.zhaojun.mapper.UserRoleMapper;
import im.zhaojun.model.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RedisSessionDAO redisSessionDAO;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public List<User> selectAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        return userMapper.selectAll();
    }

    public Integer[] selectRoleIdsById(Integer userId) {
        return userMapper.selectRoleIdsById(userId);
    }

    public Integer add(User user, Integer[] roleIds) {
        String salt = String.valueOf(System.currentTimeMillis());
        String encryptPassword = new Md5Hash(user.getPassword(), salt).toString();

        if (user.getStatus() == null) {
            user.setStatus("1");
        }

        user.setSalt(salt);
        user.setPassword(encryptPassword);
        userMapper.insert(user);

        // 赋予角色.
        userRoleMapper.insertList(user.getUserId(), roleIds);

        return user.getUserId();
    }

    public boolean updateLastLoginTimeByUsername(String username) {
        return userMapper.updateLastLoginTimeByUsername(username) == 1;
    }

    public boolean disableUserByID(Integer id) {
        offlineByUserId(id);
        return userMapper.updateStatusByPrimaryKey(id, 0) == 1;
    }

    public boolean enableUserByID(Integer id) {
        return userMapper.updateStatusByPrimaryKey(id, 1) == 1;
    }

    public boolean update(User user) {
        return userMapper.updateByPrimaryKey(user) == 1;
    }

    public User selectOne(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public boolean checkUserNameExist(String username) {
        return userMapper.countByUserName(username) == 1;
    }

    public void offlineBySessionId(String sessionId) {
        Session session = redisSessionDAO.readSession(sessionId);
        if (session != null) {
            log.debug("成功踢出 sessionId 为 :" + sessionId + "的用户.");
            session.stop();
            redisSessionDAO.delete(session);
        }
    }

    /**
     * 删除所有此用户的在线用户
     * @param userId
     */
    public void offlineByUserId(Integer userId) {
        Collection<Session> activeSessions = redisSessionDAO.getActiveSessions();
        for (Session session : activeSessions) {
            SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (simplePrincipalCollection != null) {
                User user = (User) simplePrincipalCollection.getPrimaryPrincipal();
                if (user != null && userId.equals(user.getUserId())) {
                    offlineBySessionId(String.valueOf(session.getId()));
                }
            }
        }
    }

    public void allocation(Integer userId, Integer[] roleIds) {
        // 清空原有的角色, 赋予新角色.
        userRoleMapper.deleteUserMenuByUserId(userId);
        userRoleMapper.insertList(userId, roleIds);
    }
}