package im.zhaojun.common.util;

import java.io.Serializable;
import java.util.List;

public class PageResultBean<T> implements Serializable {

    private static final long serialVersionUID = 5071118307783022228L;

    private long count;
    private int code;
    private List<T> data;

    public PageResultBean(long count, List<T> data) {
        this.count = count;
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
