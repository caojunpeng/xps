package com.cao.xps.service.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.xps.common.adminVo.MenuParams;
import com.cao.xps.common.adminVo.RoleParams;
import com.cao.xps.common.vo.DataTablesResult;
import com.cao.xps.service.menu.entity.Menu;
import com.cao.xps.service.menu.mapper.MenuMapper;
import com.cao.xps.service.role.entity.Role;
import com.cao.xps.service.role.mapper.RoleMapper;
import com.cao.xps.service.role.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-09-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public DataTablesResult<Role> dataLists(RoleParams roleParams) {
        QueryWrapper<Role> queryWrapper=new QueryWrapper<Role>().like("role_name",roleParams.getKeyword());
        List<Role> roles = roleMapper.selectList(queryWrapper);
        DataTablesResult<Role> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(roles);
        dataTablesResult.setRecordsFiltered(roles.size());
        dataTablesResult.setRecordsTotal(roles.size());
        return dataTablesResult;
    }
}
