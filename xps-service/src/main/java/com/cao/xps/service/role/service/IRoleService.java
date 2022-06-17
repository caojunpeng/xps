package com.cao.xps.service.role.service;

import com.cao.xps.common.adminVo.RoleParams;
import com.cao.xps.common.vo.DataTablesResult;
import com.cao.xps.service.role.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-09-06
 */
public interface IRoleService extends IService<Role> {

    public DataTablesResult<Role> dataLists(RoleParams roleParams);
}
