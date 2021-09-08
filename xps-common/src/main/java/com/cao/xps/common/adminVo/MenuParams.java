package com.cao.xps.common.adminVo;

import com.cao.xps.common.vo.DataTablesParams;

public class MenuParams extends DataTablesParams {
    /**
     * 关键字
     */
    private String keyword;
    /**
     * id
     */
    private String menuId;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
