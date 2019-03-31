package im.zhaojun.mapper;

import im.zhaojun.model.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(Dept dept);

    int insertSelective(Dept dept);

    Dept selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(Dept dept);

    int updateByPrimaryKey(Dept dept);

    List<Dept> selectByParentId(@Param("parentId")Integer parentId);

    List<Dept> selectAll();

    List<Integer> selectChildrenIDByPrimaryKey(Integer deptId);

    int selectMaxOrderNum();

    int swapSort(@Param("currentId")Integer currentId, @Param("swapId")Integer swapId);
}