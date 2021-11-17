package cn.gkq.camel.util;

/**
 * @author GKQ
 * @Classname CustomExchangePattern
 * @Date 2021/3/29
 */
public enum CustomExchangePattern {

    IN("IN", "IN模式"),
    IN_OUT("IN_OUT", "IN_OUT模式"),
    OUT("OUT", "OUT模式"),
    OUT_IN("OUT_IN", "OUT_IN模式");

    private String code;
    private String desc;

    CustomExchangePattern(String code, String desc) {
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
