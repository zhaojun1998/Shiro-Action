package im.zhaojun.service;

import im.zhaojun.mapper.OperatorMapper;
import im.zhaojun.model.Operator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OperatorService {

    @Resource
    private OperatorMapper operatorMapper;

    public int deleteByPrimaryKey(Integer operatorId){
        return operatorMapper.deleteByPrimaryKey(operatorId);
    }

    public int insert(Operator record){
        return operatorMapper.insert(record);
    }

    public int insertSelective(Operator record){
        return operatorMapper.insertSelective(record);
    }

    public Operator selectByPrimaryKey(Integer operatorId){
        return operatorMapper.selectByPrimaryKey(operatorId);
    }

    public int updateByPrimaryKeySelective(Operator record){
        return operatorMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Operator record){
        return operatorMapper.updateByPrimaryKey(record);
    }

    public List<Operator> selectByMenuId(Integer menuId) {
        return operatorMapper.selectByMenuId(menuId);
    }

    public List<Operator> selectAll() {
        return operatorMapper.selectAll();
    }
}
