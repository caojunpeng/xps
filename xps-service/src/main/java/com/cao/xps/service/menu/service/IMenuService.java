package com.cao.xps.service.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cao.xps.common.adminVo.MenuParams;
import com.cao.xps.common.adminVo.RoleParams;
import com.cao.xps.common.vo.DataTablesResult;
import com.cao.xps.common.ztreeVo.ZtreeData;
import com.cao.xps.service.menu.entity.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-07-19
 */
public interface IMenuService extends IService<Menu>  {

    public DataTablesResult<Menu> dataLists(MenuParams menuParams);

    public List<ZtreeData> getZtreeDatas(RoleParams roleParams);

}
