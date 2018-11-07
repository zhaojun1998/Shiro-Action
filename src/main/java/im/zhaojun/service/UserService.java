package im.zhaojun.service;

import com.github.pagehelper.PageHelper;
import im.zhaojun.mapper.UserMapper;
import im.zhaojun.model.User;
import im.zhaojun.model.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<UserVO> selectAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<User> userList = userMapper.selectAll();
        List<UserVO> userVOList = new ArrayList<>();
        for (User user : userList) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            userVOList.add(userVO);
        }
        return userVOList;
    }

    public Integer add(User user) {
        userMapper.insert(user);
        return user.getUserId();
    }

    public boolean disableUserByID(Integer id) {
        return userMapper.updateStatusByPrimaryKey(id, 0) == 1;
    }

    public boolean enableUserByID(Integer id) {
        return userMapper.updateStatusByPrimaryKey(id, 1) == 1;
    }

}
