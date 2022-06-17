package com.cao.xps.common.echart;

/**
 * Echart节点对象
 */
public class EchartsVo {
    /**
     * 节点名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EchartsVo(String name) {
        this.name = name;
    }
    public EchartsVo() {
    }
}
