package com.cao.wps.service.user.service;

import com.cao.wps.service.user.entity.User;
import com.cao.xps.common.admin.user.UserParams;
import com.cao.xps.common.vo.DataTablesResult;

public interface UserService {

    public DataTablesResult<User> dataLists(UserParams param);
}
