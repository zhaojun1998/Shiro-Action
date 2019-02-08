package im.zhaojun.model.vo;

public class UrlVO {
    private String url;
    private String type;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UrlVO(String url, String type) {
        this.url = url;
        this.type = type;
    }

    @Override
    public String toString() {
        return "UrlVO{" +
                "url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlVO urlVO = (UrlVO) o;

        if (url != null ? !url.equals(urlVO.url) : urlVO.url != null) return false;
        return type != null ? type.equals(urlVO.type) : urlVO.type == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
