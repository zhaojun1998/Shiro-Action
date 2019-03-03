package im.zhaojun.mapper;

import im.zhaojun.model.Operator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperatorMapper {
    int deleteByPrimaryKey(Integer operatorId);

    int insert(Operator record);

    int insertSelective(Operator record);

    Operator selectByPrimaryKey(Integer operatorId);

    int updateByPrimaryKeySelective(Operator record);

    int updateByPrimaryKey(Operator record);

    List<Operator> selectByMenuId(@Param("menuId")Integer menuId);

    List<Operator> selectAll();

    int deleteByMenuId(Integer menuId);
}