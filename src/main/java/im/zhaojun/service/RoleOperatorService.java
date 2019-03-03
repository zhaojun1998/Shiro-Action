package im.zhaojun.service;

import im.zhaojun.mapper.RoleOperatorMapper;
import im.zhaojun.model.RoleOperator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleOperatorService{

    @Resource
    private RoleOperatorMapper roleOperatorMapper;

    public int insert(RoleOperator record){
        return roleOperatorMapper.insert(record);
    }

    public int insertSelective(RoleOperator record){
        return roleOperatorMapper.insertSelective(record);
    }

}
