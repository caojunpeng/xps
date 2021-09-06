package com.cao.xps.admin.user;

import com.cao.xps.service.user.service.UserService;
import com.cao.xps.common.admin.user.UserParams;
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
    public ModelAndView userList(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/user/userList");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/datas")
    public Integer datas(UserParams userParams){
        return userService.dataLists("213");
    }
}
