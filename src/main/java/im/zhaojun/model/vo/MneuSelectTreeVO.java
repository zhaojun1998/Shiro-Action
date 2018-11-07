package im.zhaojun.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MneuSelectTreeVO {

    @JsonProperty("id")
    private Integer menuId;

    @JsonProperty("pid")
    private Integer parentId;

    @JsonProperty("name")
    private String menuName;

    private String url;

    private String perms;

    private String type;

    private Integer orderNum;
}
