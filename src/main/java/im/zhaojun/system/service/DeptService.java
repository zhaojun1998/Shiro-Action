package im.zhaojun.system.service;

import im.zhaojun.system.mapper.DeptMapper;
import im.zhaojun.system.model.Dept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeptService {

    @Resource
    private DeptMapper deptMapper;

    public int deleteByPrimaryKey(Integer deptId) {
        return deptMapper.deleteByPrimaryKey(deptId);
    }

    public int insert(Dept dept) {
        int maxOrderNum = deptMapper.selectMaxOrderNum();
        dept.setOrderNum(maxOrderNum + 1);
        return deptMapper.insert(dept);
    }

    public Dept selectByPrimaryKey(Integer deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }

    public int updateByPrimaryKey(Dept dept) {
        return deptMapper.updateByPrimaryKey(dept);
    }

    /**
     * 删除当前部门及子部门.
     */
    public void deleteCascadeByID(Integer deptId) {

        List<Integer> childIDList = deptMapper.selectChildrenIDByPrimaryKey(deptId);
        for (Integer childId : childIDList) {
            deleteCascadeByID(childId);
        }

        // 删除自身
        deleteByPrimaryKey(deptId);
    }

    public List<Dept> selectByParentId(Integer parentId) {
        return deptMapper.selectByParentId(parentId);
    }

    /**
     * 查找所有的部门的树形结构
     */
    @Cacheable(key = "'selectAllDeptTree'")
    public List<Dept> selectAllDeptTree() {
        return deptMapper.selectAllTree();
    }

    /**
     * 获取所有菜单并添加一个根节点 (树形结构)
     */
    public List<Dept> selectAllDeptTreeAndRoot() {
        List<Dept> deptList = toTree(selectAllDept());
        Dept root = new Dept();
        root.setDeptId(0);
        root.setDeptName("根部门");
        root.setChildren(deptList);
        List<Dept> rootList = new ArrayList<>();
        rootList.add(root);
        return rootList;
    }

    public void swapSort(Integer currentId, Integer swapId) {
        deptMapper.swapSort(currentId, swapId);
    }
}
