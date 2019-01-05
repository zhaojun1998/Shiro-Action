package im.zhaojun.model.vo;

import java.io.Serializable;

public class RoleMenuVO implements Serializable {
    private static final long serialVersionUID = -1003658793942956175L;

    private String roleId;
    private String roleName;
    private boolean own;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean getOwn() {
        return own;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }
}
