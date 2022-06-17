package com.cao.xps.service.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author jobob
 * @since 2021-07-19
 */
@TableName("Menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 标签
     */
    private String menuTitle;

    /**
     * 父级名称
     */
    private String parentName;

    /**
     * 请求地址
     */
    private String action;

    /**
     * 排序
     */
    private Integer orderBy;

    /**
     * 状态:1可用;0:禁用
     */
    private Integer status;

    /**
     * 图标
     */
    private String icon;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 点击菜单触发函数
     */
    private String menuFunction;

    /**
     * 菜单样式名
     */
    private String menuStyle;


    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getMenuFunction() {
        return menuFunction;
    }

    public void setMenuFunction(String menuFunction) {
        this.menuFunction = menuFunction;
    }
    public String getMenuStyle() {
        return menuStyle;
    }

    public void setMenuStyle(String menuStyle) {
        this.menuStyle = menuStyle;
    }

    @Override
    public String toString() {
        return "Menu{" +
            "menuId=" + menuId +
            ", menuName=" + menuName +
            ", menuTitle=" + menuTitle +
            ", parentName=" + parentName +
            ", action=" + action +
            ", orderBy=" + orderBy +
            ", status=" + status +
            ", icon=" + icon +
            ", createTime=" + createTime +
            ", creator=" + creator +
            ", menuFunction=" + menuFunction +
            ", menuStyle=" + menuStyle +
        "}";
    }
}
