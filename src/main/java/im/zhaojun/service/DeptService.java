package im.zhaojun.service;

import im.zhaojun.mapper.DeptMapper;
import im.zhaojun.model.Dept;
import im.zhaojun.util.TreeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeptService{

    @Resource
    private DeptMapper deptMapper;

    public int deleteByPrimaryKey(Integer deptId){
        return deptMapper.deleteByPrimaryKey(deptId);
    }

    public int insert(Dept record){
        return deptMapper.insert(record);
    }

    public int insertSelective(Dept record){
        return deptMapper.insertSelective(record);
    }

    public Dept selectByPrimaryKey(Integer deptId){
        return deptMapper.selectByPrimaryKey(deptId);
    }

    public int updateByPrimaryKeySelective(Dept record){
        return deptMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Dept record){
        return deptMapper.updateByPrimaryKey(record);
    }

    public void deleteByIDAndChildren(Integer deptId) {}

    public List<Dept> selectByParentId(Integer parentId) {
        return deptMapper.selectByParentId(parentId);
    }

    /**
     * 查找所有的部门
     */
    public List<Dept> selectAllDept() {
        return deptMapper.selectAll();
    }

    /**
     * 查找所有的部门的树形结构
     */
    public List<Dept> selectAllDeptTree() {
        return toTree(selectAllDept());
    }

    /**
     *  获取所有菜单并添加一个根节点 (树形结构)
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

    /**
     * 调用工具类, 将部门列表转化为部门树
     */
    private List<Dept> toTree(List<Dept> depts) {
        return TreeUtil.toTree(depts, "deptId", "parentId", "children", Dept.class);
    }
}
