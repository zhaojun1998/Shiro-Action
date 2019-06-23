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

    public Dept insert(Dept dept) {
        int maxOrderNum = deptMapper.selectMaxOrderNum();
        dept.setOrderNum(maxOrderNum + 1);
        deptMapper.insert(dept);
        return dept;
    }

    public int deleteByPrimaryKey(Integer deptId) {
        return deptMapper.deleteByPrimaryKey(deptId);
    }

    public Dept updateByPrimaryKey(Dept dept) {
        deptMapper.updateByPrimaryKey(dept);
        return dept;
    }

    public Dept selectByPrimaryKey(Integer deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }


    /**
     * 删除当前部门及子部门.
     */
    public void deleteCascadeByID(Integer deptId) {

        List<Integer> childIDList = deptMapper.selectChildrenIDByPrimaryKey(deptId);
        for (Integer childId : childIDList) {
            deleteCascadeByID(childId);
        }

        deleteByPrimaryKey(deptId);
    }

    /**
     * 根据父 ID 查询部门
     */
    public List<Dept> selectByParentId(Integer parentId) {
        return deptMapper.selectByParentId(parentId);
    }

    /**
     * 查找所有的部门的树形结构
     */
    public List<Dept> selectAllDeptTree() {
        return deptMapper.selectAllTree();
    }

    /**
     * 获取所有菜单并添加一个根节点 (树形结构)
     */
    public List<Dept> selectAllDeptTreeAndRoot() {
        List<Dept> deptList = selectAllDeptTree();
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
