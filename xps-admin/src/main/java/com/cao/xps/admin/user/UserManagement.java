package com.cao.xps.admin.user;

import com.cao.wps.service.user.entity.User;
import com.cao.wps.service.user.service.UserService;
import com.cao.xps.common.admin.user.UserParams;
import com.cao.xps.common.vo.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserManagement {

    @Resource
    private UserService userService;

    @RequestMapping("/userList")
    public ModelAndView userList(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/user/userList");
        return modelAndView;
    }

    @RequestMapping("/datas")
    public DataTablesResult<User> datas(UserParams userParams){
        return userService.dataLists(userParams);
    }
}
