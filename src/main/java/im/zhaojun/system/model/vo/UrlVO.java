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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UrlVO urlVO = (UrlVO) o;

        if (url != null ? !url.equals(urlVO.url) : urlVO.url != null) {
            return false;
        }
        return (method != null ? method.equals(urlVO.method) : urlVO.method == null) && (type != null ? type.equals(urlVO.type) : urlVO.type == null);

    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UrlVO{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
