package com.cao.xps.service.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cao.xps.service.user.entity.User;

public interface UserService extends IService<User> {

    Integer dataLists(String  param);
}
