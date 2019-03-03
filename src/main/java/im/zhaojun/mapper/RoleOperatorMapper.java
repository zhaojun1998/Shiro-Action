package im.zhaojun.mapper;

import im.zhaojun.model.RoleOperator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleOperatorMapper {
    int insert(RoleOperator record);

    int insertSelective(RoleOperator record);

    Integer[] getOperatorsByRoleId(Integer roleId);

    int deleteByRoleId(@Param("roleId")Integer roleId);

    int insertRoleOperators(@Param("roleId")Integer roleId, @Param("operatorIds")Integer[] operatorIds);

    int deleteByOperatorId(@Param("operatorId")Integer operatorId);
}