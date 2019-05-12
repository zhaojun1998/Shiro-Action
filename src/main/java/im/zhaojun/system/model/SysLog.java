package im.zhaojun.system.model;

import java.util.Date;

public class SysLog {
    private Integer id;

    /**
	* 用户名
	*/
    private String username;

    /**
	* 操作
	*/
    private String operation;

    /**
	* 响应时间/耗时
	*/
    private Integer time;

    /**
	* 请求方法
	*/
    private String method;

    /**
	* 请求参数
	*/
    private String params;

    /**
	* IP
	*/
    private String ip;

    /**
	* 创建时间
	*/
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}