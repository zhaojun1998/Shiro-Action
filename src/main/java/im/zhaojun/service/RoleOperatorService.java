package im.zhaojun.service;

import im.zhaojun.mapper.RoleOperatorMapper;
import im.zhaojun.model.RoleOperator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleOperatorService{

    @Resource
    private RoleOperatorMapper roleOperatorMapper;

    public int insert(RoleOperator roleOperator){
        return roleOperatorMapper.insert(roleOperator);
    }

    public int insertSelective(RoleOperator roleOperator){
        return roleOperatorMapper.insertSelective(roleOperator);
    }

}
