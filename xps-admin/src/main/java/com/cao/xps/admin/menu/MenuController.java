package com.cao.xps.admin.menu;

import com.cao.wps.service.menu.entity.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
