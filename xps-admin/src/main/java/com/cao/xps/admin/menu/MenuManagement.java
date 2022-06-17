package com.cao.xps.admin.menu;

import com.cao.xps.common.adminVo.MenuParams;
import com.cao.xps.common.vo.DataTablesResult;
import com.cao.xps.service.menu.entity.Menu;
import com.cao.xps.service.menu.service.IMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/menu")
public class MenuManagement {

    @Resource
    private IMenuService menuService;


    @RequestMapping("/menuList")
    public ModelAndView menuList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/menu/menuList");
        return modelAndView;
    }


}
