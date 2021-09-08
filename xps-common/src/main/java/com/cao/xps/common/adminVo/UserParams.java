package com.cao.xps.common.adminVo;

import com.cao.xps.common.vo.DataTablesParams;

public class UserParams extends DataTablesParams {

    /**
     * 关键字
     */
    private String keyword;
    /**
     * id
     */
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
