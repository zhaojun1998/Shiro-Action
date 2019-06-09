package im.zhaojun.common.constants;

/**
 * 登录认证类型 枚举类
 */
public enum AuthcTypeEnum {

    GITHUB("Github"), GITEE("Gitee");

    private String description;

    AuthcTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
