package com.cao.xps.admin.user;

import com.cao.xps.common.vo.DataTablesResult;
import com.cao.xps.service.user.entity.User;
import com.cao.xps.service.user.service.UserService;
import com.cao.xps.common.adminVo.UserParams;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserManagement {

    @Resource
    private UserService userService;

    @RequestMapping("/userList")
    public ModelAndView userList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/userList");
        return modelAndView;
    }
    @RequestMapping("/editUser")
    public ModelAndView editUser(UserParams userParams) {
        ModelAndView modelAndView = new ModelAndView();
        if(userParams!=null && StringUtils.isNotBlank(userParams.getUserId())){
            User userByUserId = userService.getUserByUserId(userParams);
            modelAndView.addObject("user",userByUserId);
        }
        modelAndView.setViewName("/user/editUser");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/datas")
    public DataTablesResult<User> datas(UserParams userParams) {
        return userService.dataLists(userParams);
    }

    @ResponseBody
    @RequestMapping("/delUser")
    public boolean delUser(UserParams userParams) {
        return userService.delUser(userParams)>0;
    }

    @ResponseBody
    @RequestMapping("/saveUser")
    public boolean saveUser(User user) {
        return userService.saveUser(user)>0;
    }
}
