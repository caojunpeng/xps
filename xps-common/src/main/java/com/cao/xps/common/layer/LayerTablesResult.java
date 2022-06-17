package com.cao.xps.common.layer;

import java.io.Serializable;
import java.util.List;

/**
 *  layerTable返回值
 */
public class LayerTablesResult<T> implements Serializable {

    private static final long serialVersionUID = 6919399050060919454L;

    private int code=0;

    private String msg;

    /**
     * 总数
     */
    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    /**
     * 数据列表
     */
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
