package im.zhaojun.service;

import com.github.pagehelper.PageHelper;
import im.zhaojun.mapper.UserMapper;
import im.zhaojun.model.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<User> selectAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        return userMapper.selectAll();
    }

    public Integer add(User user) {
        String salt = String.valueOf(System.currentTimeMillis());
        String encryptPassword = new Md5Hash(user.getPassword(), salt).toString();

        user.setSalt(salt);
        user.setPassword(encryptPassword);
        userMapper.insert(user);
        return user.getUserId();
    }

    public boolean updateLastLoginTimeByUsername(String username) {
        return userMapper.updateLastLoginTimeByUsername(username) == 1;
    }

    public boolean disableUserByID(Integer id) {
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
}
