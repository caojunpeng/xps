package com.cao.xps.app.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.xps.service.menu.entity.Menu;
import com.cao.xps.service.menu.service.IMenuService;
import com.cao.xps.service.user.entity.User;
import com.cao.xps.service.user.mapper.UserMapper;
import com.cao.xps.common.shiro.SaltUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class LoginController {

    @Autowired
    private UserMapper userMapper;
    @Resource
    private IMenuService menuService;


    @RequestMapping("/loginHtml")
    public String login(String userName, String userPwd) {
        //获取主题对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(userName,userPwd));
            System.out.println("登录成功！！！");
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            System.out.println("用户错误！！！");
            return "redirect:/login";
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误！！！");
            return "redirect:/login";
        }
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView();
        Object object = SecurityUtils.getSubject().getPrincipal();
        if(object!=null){
            modelAndView.addObject("userName",object);
        }
        QueryWrapper<Menu> menuQueryWrapper=new QueryWrapper<Menu>().eq("parent_name","main").eq("status",1);
        List<Menu> list = menuService.list(menuQueryWrapper);
        modelAndView.addObject("menulist",list);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login/login");
        return modelAndView;
    }
    @RequestMapping("/saveUserPage")
    public ModelAndView saveUserPage() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("user/addUser");
        return modelAndView;
    }
    @RequestMapping("/saveUser")
    public String register(User user) {
        try {
            //1.获取随机盐
            String salt = SaltUtil.getSalt(8);
            //2.将随机盐保存到数据
            user.setSalt(salt);
            //3.明文密码进行md5 + salt + hash散列
            Md5Hash md5 = new Md5Hash(user.getUserPwd(),salt,1024);
            user.setUserPwd(md5.toHex());
            userMapper.insert(user);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login";
        }

    }
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

}

