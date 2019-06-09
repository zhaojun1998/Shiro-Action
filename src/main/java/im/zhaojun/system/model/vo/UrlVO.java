package im.zhaojun.system.model.vo;

import java.util.Objects;

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

        if (!Objects.equals(url, urlVO.url)) {
            return false;
        }
        return (Objects.equals(method, urlVO.method)) && (Objects.equals(type, urlVO.type));

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
