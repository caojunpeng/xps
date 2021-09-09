package com.cao.xps.common.adminVo;

import com.cao.xps.common.vo.DataTablesParams;

public class RoleParams extends DataTablesParams {
    /**
     * 关键字
     */
    private String keyword;
    /**
     * id
     */
    private String roleId;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
