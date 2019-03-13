package im.zhaojun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Dept {
    /**
	* 部门ID
	*/
    private Integer deptId;

    /**
	* 部门名称
	*/
    private String deptName;

    /**
	* 上级部门ID
	*/
    private Integer parentId;

    /**
	* 排序
	*/
    private Integer orderNum;

    /**
	* 创建时间
	*/
    @JsonIgnore
    private Date createTime;

    /**
	* 修改时间
	*/
    @JsonIgnore
    private Date modifyTime;

    @JsonIgnore
    private String checkArr;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(String checkArr) {
        this.checkArr = checkArr;
    }
}