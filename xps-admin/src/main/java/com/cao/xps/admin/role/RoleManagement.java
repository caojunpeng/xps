package com.cao.xps.admin.role;

import com.alibaba.fastjson.JSON;
import com.cao.xps.common.adminVo.RoleParams;
import com.cao.xps.common.vo.DataTablesResult;
import com.cao.xps.common.ztreeVo.ZtreeData;
import com.cao.xps.service.menu.entity.Menu;
import com.cao.xps.service.menu.service.IMenuService;
import com.cao.xps.service.role.entity.Role;
import com.cao.xps.service.role.service.IRoleService;
import com.cao.xps.service.roleMenu.entity.RoleMenu;
import com.cao.xps.service.roleMenu.service.IRoleMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleManagement {

    @Resource
    private IRoleService roleService;
    @Resource
    private IMenuService menuService;
    @Resource
    private IRoleMenuService roleMenuService;


    @RequestMapping("/roleList")
    public ModelAndView roleList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/role/rolesList");
        return modelAndView;
    }
    @RequestMapping("/roleMenuList")
    public ModelAndView roleMenulist(RoleParams roleParams) {
        ModelAndView modelAndView = new ModelAndView();
        List<ZtreeData> ztreeDatas = menuService.getZtreeDatas(roleParams);
        modelAndView.addObject("ztreeDatas", JSON.toJSON(ztreeDatas));
        modelAndView.addObject("roleId",roleParams.getRoleId());
        modelAndView.setViewName("/role/roleMenuList");
        return modelAndView;
    }
    @RequestMapping("/editrole")
    public ModelAndView editrole(RoleParams roleParams) {
        ModelAndView modelAndView = new ModelAndView();
        if(roleParams!=null){
            Role roleByroleId = roleService.getById(roleParams.getRoleId());
            modelAndView.addObject("role",roleByroleId);
        }
        modelAndView.setViewName("/role/editrole");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/datas")
    public DataTablesResult<Role> datas(RoleParams roleParams) {
        return roleService.dataLists(roleParams);
    }

    @ResponseBody
    @RequestMapping("/delrole")
    public boolean delrole(RoleParams roleParams) {
        return roleService.removeById(roleParams.getRoleId());
    }

    @ResponseBody
    @RequestMapping("/saverole")
    public boolean saverole(Role role) {
        role.setCreateTime(LocalDateTime.now());
        return roleService.saveOrUpdate(role);
    }
    @ResponseBody
    @RequestMapping("/addRoleMenu")
    public boolean addRoleMenu(RoleParams roleParams) {
        return roleMenuService.saveRoleMenu(roleParams);
    }
}
