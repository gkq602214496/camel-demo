package cn.gkq.camel.util;

/**
 * @author GKQ
 * @Classname RouteType
 * @Description ROUTE类型枚举
 * @Date 2021/3/26
 */
public enum RouteType {

    HTTP("http", "http"),
    JDBC("jdbc", "jdbc");

    private String code;
    private String desc;

    RouteType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
