package com.cao.xps.admin.management;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.xps.service.menu.entity.Menu;
import com.cao.xps.service.menu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControlle {

    @Autowired
    private MenuMapper menuMapper;

    @RequestMapping("/adminPage")
    public ModelAndView adminPage(){
        ModelAndView modelAndView=new ModelAndView();
        //所有父级菜单
        QueryWrapper<Menu> menuQueryWrapper=new QueryWrapper<Menu>().eq("status","1").eq("parent_name","admin").orderByAsc("order_by");
        List<Menu> parentMenu = menuMapper.selectList(menuQueryWrapper);
        modelAndView.addObject("parentMenu",parentMenu);
        //所有子集菜单
        QueryWrapper<Menu> menuQueryWrapper1=new QueryWrapper<Menu>().eq("status","1").ne("parent_name","admin").orderByAsc("order_by");
        List<Menu> menuList = menuMapper.selectList(menuQueryWrapper1);
        modelAndView.addObject("menuList",menuList);
        modelAndView.setViewName("/admin");
        return modelAndView;
    }
}
