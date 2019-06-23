package im.zhaojun.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import im.zhaojun.common.validate.groups.Create;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = -3200103254689137288L;

    private Integer userId;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @JsonIgnore
    @NotBlank(message = "密码不能为空", groups = Create.class)
    private String password;

    @JsonIgnore
    private String salt;

    @Email(message = "邮箱格式不正确")
    private String email;

    private String status;

    private Date lastLoginTime;

    private Date createTime;

    @JsonIgnore
    private Date modifyTime;

    @JsonIgnore
    private String activeCode;

    private Integer deptId;

    private String deptName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", activeCode='" + activeCode + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}