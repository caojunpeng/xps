package com.cao.xps.service.userRole.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.xps.service.user.entity.User;
import com.cao.xps.service.user.mapper.UserMapper;
import com.cao.xps.service.userRole.entity.UserRole;
import com.cao.xps.service.userRole.mapper.UserRoleMapper;
import com.cao.xps.service.userRole.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户权限 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-07-19
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>  implements IUserRoleService {

}
