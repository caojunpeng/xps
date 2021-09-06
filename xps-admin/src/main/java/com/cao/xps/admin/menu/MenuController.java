package com.cao.xps.admin.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/menu")
public class MenuController {

    @RequestMapping("/userList")
    public ModelAndView userList(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/user/userList");
        return modelAndView;
    }
}
