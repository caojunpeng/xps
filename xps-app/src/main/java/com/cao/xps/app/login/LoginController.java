package com.cao.xps.app.login;

import com.alibaba.fastjson.JSON;
import com.cao.wps.service.user.entity.User;
import com.cao.wps.service.user.mapper.UserMapper;
import com.cao.xps.app.shiro.SaltUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class LoginController {

    @Autowired
    private UserMapper userMapper;


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
        modelAndView.setViewName("index/index");
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
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
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
            List<User> list = userMapper.selectList(null);
            System.out.println(JSON.toJSON(list));
            user.setUserPwd(md5.toHex());
            userMapper.insert(user);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login";
        }
    }


}

