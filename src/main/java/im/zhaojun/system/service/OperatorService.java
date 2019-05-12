package im.zhaojun.system.service;

import im.zhaojun.common.util.TreeUtil;
import im.zhaojun.system.mapper.MenuMapper;
import im.zhaojun.system.mapper.OperatorMapper;
import im.zhaojun.system.mapper.RoleOperatorMapper;
import im.zhaojun.system.model.Menu;
import im.zhaojun.system.model.Operator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    public int add(Operator operator){
        return operatorMapper.insert(operator);
    }

    public Operator selectByPrimaryKey(Integer operatorId){
        return operatorMapper.selectByPrimaryKey(operatorId);
    }

    public int updateByPrimaryKey(Operator operator){
        return operatorMapper.updateByPrimaryKey(operator);
    }

    public List<Operator> selectByMenuId(Integer menuId) {
        return operatorMapper.selectByMenuId(menuId);
    }

    public List<Operator> selectAll() {
        return operatorMapper.selectAll();
    }

    public List<Menu> getALLMenuAndOperatorTree() {

        // 获取用户拥有的所在操作权限
        List<Operator> operators = operatorMapper.selectAll();

        List<Menu> menuList = menuMapper.selectAll();

        // 获取功能权限树时, 菜单应该没有复选框, 不可选.
        for (Menu menu : menuList) {
            menu.setCheckArr(null);
        }

        List<Menu> menuTree = TreeUtil.toTree(menuList,
                "menuId", "parentId", "children", Menu.class);



        List<Menu> menuLeafNode = TreeUtil.getAllLeafNode(menuTree);

        // 将操作权限拼接到页面的树形结构下.
        for (Menu menu : menuLeafNode) {

            List<Menu> children = menu.getChildren();
            if (children == null) {
                children = new ArrayList<>();
            }

            for (Operator operator : operators) {
                if (menu.getMenuId().equals(operator.getMenuId())) {

                    // 将操作权限转化为 Menu 结构. 由于操作权限可能与菜单权限的 ID 值冲突, 故将操作权限的 ID + 10000. 使用操作权限的 ID 时再减去这个数
                    Menu temp = new Menu();
                    temp.setMenuId(operator.getOperatorId() + 10000);
                    temp.setParentId(operator.getMenuId());
                    temp.setMenuName(operator.getOperatorName());
                    children.add(temp);
                }
            }
            menu.setChildren(children);
        }

        return menuTree;
    }
}
