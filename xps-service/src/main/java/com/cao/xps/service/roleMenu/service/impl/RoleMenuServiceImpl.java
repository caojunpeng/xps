package com.cao.xps.service.roleMenu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.xps.common.adminVo.RoleParams;
import com.cao.xps.service.role.entity.Role;
import com.cao.xps.service.roleMenu.entity.RoleMenu;
import com.cao.xps.service.roleMenu.mapper.RoleMenuMapper;
import com.cao.xps.service.roleMenu.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-09-13
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public boolean saveRoleMenu(RoleParams roleParams){
        boolean resule=false;
        //先清空
        QueryWrapper<RoleMenu> queryWrapper=new QueryWrapper<RoleMenu>().eq("role_id",roleParams.getRoleId());
        roleMenuMapper.delete(queryWrapper);
        //插入
        Integer[] menuIdList = roleParams.getMenuIdList();
        if(menuIdList!=null && menuIdList.length>0){
            for(Integer menuId:menuIdList){
                RoleMenu roleMenu=new RoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(roleParams.getRoleId());
                roleMenuMapper.insert(roleMenu);
            }
            resule=true;
        }
        return resule;
    }
}
