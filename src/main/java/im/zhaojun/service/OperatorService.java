package im.zhaojun.service;

import im.zhaojun.mapper.MenuMapper;
import im.zhaojun.mapper.OperatorMapper;
import im.zhaojun.mapper.RoleOperatorMapper;
import im.zhaojun.model.Menu;
import im.zhaojun.model.Operator;
import im.zhaojun.model.vo.MenuTreeVO;
import im.zhaojun.util.TreeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OperatorService {

    @Resource
    private OperatorMapper operatorMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleOperatorMapper roleOperatorMapper;

    public void deleteByPrimaryKey(Integer operatorId) {
        // 删除分配给用户的操作权限
        roleOperatorMapper.deleteByOperatorId(operatorId);
        // 删除自身
        operatorMapper.deleteByPrimaryKey(operatorId);
    }

    public int add(Operator record){
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

    public List<MenuTreeVO> getALLMenuAndOperatorTreeVO() {

        // 获取用户拥有的所在操作权限
        List<Operator> operators = operatorMapper.selectAll();

        List<Menu> menus = menuMapper.selectAll();
        List<MenuTreeVO> menuTreeVOS = TreeUtil.menuListToMenuTree(menus);

        List<MenuTreeVO> leafNodeMenuByMenuTreeVO = TreeUtil.getLeafNodeMenuByMenuTreeVO(menuTreeVOS);

        for (MenuTreeVO menuTreeVO : leafNodeMenuByMenuTreeVO) {
            List<MenuTreeVO> children = menuTreeVO.getChildren();
            for (Operator operator : operators) {
                if (menuTreeVO.getMenuId().equals(operator.getMenuId())) {
                    MenuTreeVO temp = new MenuTreeVO();
                    temp.setMenuId(operator.getOperatorId());
                    temp.setParentId(operator.getMenuId());
                    temp.setMenuName(operator.getOperatorName());
                    temp.setCheckArr("0"); // 允许复选框
                    children.add(temp);
                }
            }
        }

        return menuTreeVOS;
    }
}
