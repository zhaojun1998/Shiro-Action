package im.zhaojun.system.model.vo;

import java.io.Serializable;
import java.util.Date;

public class UserVO implements Serializable {

    private static final long serialVersionUID = 8572826955489005982L;

    private Integer userId;

    private String username;

    private String email;

    private String status;

    private String deptName;

    private Date lastLoginTime;

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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", deptName='" + deptName + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
