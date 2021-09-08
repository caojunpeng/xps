package com.cao.xps.service.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cao.xps.common.adminVo.UserParams;
import com.cao.xps.common.vo.DataTablesResult;
import com.cao.xps.service.user.entity.User;

public interface UserService extends IService<User> {

    DataTablesResult<User> dataLists(UserParams userParams);

    public Integer delUser(UserParams userParams);

    public User getUserByUserId(UserParams userParams);

    public Integer saveUser(User user);
}
