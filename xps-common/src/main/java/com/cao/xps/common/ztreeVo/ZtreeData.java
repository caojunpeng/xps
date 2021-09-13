package com.cao.xps.common.ztreeVo;

import java.awt.*;
import java.util.List;

/*ztree实体类*/
public class ZtreeData {

    private String name;
    private boolean open;
    private boolean checked;
    private List<ZtreeData> children;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<ZtreeData> getChildren() {
        return children;
    }

    public void setChildren(List<ZtreeData> children) {
        this.children = children;
    }
}
