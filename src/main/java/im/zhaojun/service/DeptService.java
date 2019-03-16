package im.zhaojun.service;

import im.zhaojun.mapper.DeptMapper;
import im.zhaojun.model.Dept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private List<Dept> selectAllDept() {
        return deptMapper.selectAll();
    }


//    /**
//     * 获取所有菜单 (树形结构)
//     */
//    public List<DeptTreeVO> getALLMenuTreeVO() {
//        List<Dept> depts = selectAllDept();
//        for (Dept dept : depts) {
//            dept.setCheckArr("0");
//        }
//        return TreeUtil.menuListToMenuTree(depts);
//    }
//
//
//    /**
//     * 获取所有菜单并添加一个根节点 (树形结构)
//     */
//    public List<MenuTreeVO> getALLMenuTreeVOAndRoot() {
//        List<MenuTreeVO> allMenuTreeVO = getALLMenuTreeVO();
//        MenuTreeVO root = new MenuTreeVO();
//        root.setMenuId(0);
//        root.setMenuName("导航目录");
//        root.setChildren(allMenuTreeVO);
//        List<MenuTreeVO> rootList = new ArrayList<>();
//        rootList.add(root);
//        return rootList;
//    }
}
