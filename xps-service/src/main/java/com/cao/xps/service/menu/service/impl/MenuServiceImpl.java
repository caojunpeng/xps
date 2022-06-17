package com.cao.xps.service.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.xps.common.adminVo.MenuParams;
import com.cao.xps.common.adminVo.RoleParams;
import com.cao.xps.common.vo.DataTablesResult;
import com.cao.xps.common.ztreeVo.ZtreeData;
import com.cao.xps.service.menu.entity.Menu;
import com.cao.xps.service.menu.mapper.MenuMapper;
import com.cao.xps.service.menu.service.IMenuService;
import com.cao.xps.service.roleMenu.entity.RoleMenu;
import com.cao.xps.service.roleMenu.service.IRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-07-19
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>  implements IMenuService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private IRoleMenuService roleMenuService;

    @Override
    public DataTablesResult<Menu> dataLists(MenuParams menuParams) {
        QueryWrapper<Menu> queryWrapper=new QueryWrapper<Menu>().like("menu_name",menuParams.getKeyword());
        List<Menu> menus = menuMapper.selectList(queryWrapper);
        DataTablesResult<Menu> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(menus);
        dataTablesResult.setRecordsFiltered(menus.size());
        dataTablesResult.setRecordsTotal(menus.size());
        return dataTablesResult;
    }

    /**
     * 拼接菜单树
     * @param
     * @return
     */
    @Override
    public List<ZtreeData> getZtreeDatas(RoleParams roleParams) {
        QueryWrapper<RoleMenu> roleMenuQueryWrapper=new QueryWrapper<RoleMenu>().eq("role_id",roleParams.getRoleId());
        List<RoleMenu> roleMenuList = roleMenuService.list(roleMenuQueryWrapper);
        List<Integer> menuIdList=new ArrayList<>();
        if(!roleMenuList.isEmpty()){
            for (RoleMenu roleMenu : roleMenuList) {
                menuIdList.add(roleMenu.getMenuId());
            }
        }
        List<ZtreeData> datas=new ArrayList<>();
        QueryWrapper<Menu> queryWrapper=new QueryWrapper<Menu>().eq("parent_name","admin");
        List<Menu> menus = menuMapper.selectList(queryWrapper);//一级菜单
        if(menus!=null && !menus.isEmpty()){
            for(Menu menu:menus){
                ZtreeData ztreeData=new ZtreeData();
                ztreeData.setName(menu.getMenuTitle());
                ztreeData.setId(menu.getMenuId());
                ztreeData.setOpen(true);
                if(menuIdList.contains(menu.getMenuId())){
                    ztreeData.setChecked(true);
                }
                List<ZtreeData> dataObjs=new ArrayList<>();
                QueryWrapper<Menu> menuQueryWrapper=new QueryWrapper<Menu>().eq("parent_name",menu.getMenuName());
                List<Menu> menuList = menuMapper.selectList(menuQueryWrapper);
                if(menuList!=null && !menuList.isEmpty()){
                    for(Menu menuOne:menuList){
                        ZtreeData ztreeDataOne=new ZtreeData();
                        ztreeDataOne.setName(menuOne.getMenuTitle());
                        ztreeDataOne.setId(menuOne.getMenuId());
                        ztreeDataOne.setOpen(true);
                        if(menuIdList.contains(menuOne.getMenuId())){
                            ztreeDataOne.setChecked(true);
                        }
                        dataObjs.add(ztreeDataOne);
                    }
                }
                ztreeData.setChildren(dataObjs);
                datas.add(ztreeData);
            }
        }
        return datas;
    }

}
