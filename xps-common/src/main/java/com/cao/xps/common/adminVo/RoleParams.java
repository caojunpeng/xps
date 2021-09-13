package com.cao.xps.common.adminVo;

import com.cao.xps.common.vo.DataTablesParams;
import jdk.nashorn.internal.ir.GetSplitState;

import java.util.List;

public class RoleParams extends DataTablesParams {
    /**
     * 关键字
     */
    private String keyword;
    /**
     * id
     */
    private Integer roleId;
    /**
     * 授权菜单
     */
    private Integer[] menuIdList;

    public Integer[] getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(Integer[] menuIdList) {
        this.menuIdList = menuIdList;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
