package im.zhaojun.system.model;

/**
 * @author Zhao Jun
 * 2019/5/26 15:49
 */
public class UserAuths {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户 ID
     */
    private Integer userId;

    /**
     * 登录类型
     */
    private String identityType;

    /**
     * 第三方登录的用户名
     */
    private String identifier;

    /**
     * 第三方登录 token
     */
    private String credential;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}