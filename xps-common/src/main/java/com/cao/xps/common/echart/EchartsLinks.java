package com.cao.xps.common.echart;

/**
 * Echart连线对象
 */
public class EchartsLinks {

    /**
     * 来源
     */
    private  String source;

    /**
     * 目标
     */
    private  String target;

    /**
     * 线上的值
     */
    private String value;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EchartsLinks(String source, String target,String value) {
        this.source = source;
        this.target = target;
        this.value = value;
    }

    public EchartsLinks() {

    }
}
