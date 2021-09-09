package com.cao.xps.admin.role;

import com.cao.xps.common.adminVo.RoleParams;
import com.cao.xps.common.vo.DataTablesResult;
import com.cao.xps.service.role.entity.Role;
import com.cao.xps.service.role.service.IRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/role")
public class RoleManagement {

    @Resource
    private IRoleService roleService;


    @RequestMapping("/roleList")
    public ModelAndView roleList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/role/rolesList");
        return modelAndView;
    }
    @RequestMapping("/editrole")
    public ModelAndView editrole(RoleParams roleParams) {
        ModelAndView modelAndView = new ModelAndView();
        if(roleParams!=null && StringUtils.isNotBlank(roleParams.getRoleId())){
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
        return roleService.saveOrUpdate(role);
    }
}
