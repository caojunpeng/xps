package com.cao.xps.service.roleMenu.service;

import com.cao.xps.common.adminVo.RoleParams;
import com.cao.xps.service.roleMenu.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2021-09-13
 */
public interface IRoleMenuService extends IService<RoleMenu> {
    public boolean saveRoleMenu(RoleParams roleParams);
}
