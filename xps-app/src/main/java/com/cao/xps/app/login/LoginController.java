package com.cao.xps.app.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("/")
    public ModelAndView login(){
        ModelAndView view=new ModelAndView();
        view.setViewName("login/login");
        return view;
    }
}

