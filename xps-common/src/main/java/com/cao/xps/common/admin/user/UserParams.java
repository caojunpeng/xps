package com.cao.xps.common.admin.user;

import com.cao.xps.common.vo.DataTablesParams;

public class UserParams extends DataTablesParams {

    /**
     * 关键字
     */
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
