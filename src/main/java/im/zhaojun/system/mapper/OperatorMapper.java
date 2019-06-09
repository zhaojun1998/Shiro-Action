package im.zhaojun.system.mapper;

import im.zhaojun.system.model.Operator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperatorMapper {
    int deleteByPrimaryKey(Integer operatorId);

    int insert(Operator operator);

    Operator selectByPrimaryKey(Integer operatorId);

    int updateByPrimaryKey(Operator operator);

    List<Operator> selectByMenuId(@Param("menuId") Integer menuId);

    List<Operator> selectAll();

    int deleteByMenuId(Integer menuId);
}