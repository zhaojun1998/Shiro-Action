package im.zhaojun.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

public class MenuTreeVO implements Serializable {

    private static final long serialVersionUID = -47759575689227687L;

    @JsonProperty("id")
    private Integer menuId;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
    private List<MenuTreeVO> children;

    @JsonProperty("name")
    private String menuName;

    @JsonIgnore
    private String url;

    @JsonIgnore
    private String perms;

    private Integer parentId;

    /**
     * 辅助属性, 用于记录
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String checkArr = "0";

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public List<MenuTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeVO> children) {
        this.children = children;
    }

    public String getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(String checkArr) {
        this.checkArr = checkArr;
    }
}
