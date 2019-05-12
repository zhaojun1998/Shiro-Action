package im.zhaojun.system.model.vo;

public class UrlVO {
    private String url;
    private String method;
    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UrlVO(String url, String method, String type) {
        this.url = url;
        this.method = method;
        this.type = type;
    }
}
