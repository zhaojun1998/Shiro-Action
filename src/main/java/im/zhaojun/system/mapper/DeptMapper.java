package im.zhaojun.system.mapper;

import im.zhaojun.system.model.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptMapper {

    int deleteByPrimaryKey(Integer deptId);

    int insert(Dept dept);

    Dept selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKey(Dept dept);

    List<Dept> selectByParentId(@Param("parentId") Integer parentId);

    List<Dept> selectAllTree();

    List<Integer> selectChildrenIDByPrimaryKey(@Param("deptId") Integer deptId);

    int selectMaxOrderNum();

    int swapSort(@Param("currentId") Integer currentId, @Param("swapId") Integer swapId);

}