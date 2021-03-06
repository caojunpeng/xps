package com.cao.xps.app.shiro;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.xps.service.menu.mapper.MenuMapper;
import com.cao.xps.service.role.mapper.RoleMapper;
import com.cao.xps.service.roleMenu.mapper.RoleMenuMapper;
import com.cao.xps.service.user.entity.User;
import com.cao.xps.service.user.mapper.UserMapper;
import com.cao.xps.service.userRole.mapper.UserRoleMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    /**
     * 授权(暂时不通)
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取登录用户名
        String principal = (String) authenticationToken.getPrincipal();
        if(StringUtils.isNotBlank(principal)){
            QueryWrapper<User> userQueryWrapper=new QueryWrapper<User>().eq("user_name",principal);
            //获取库中登录用户信息
            User user = userMapper.selectOne(userQueryWrapper);
            if (!ObjectUtils.isEmpty(user)) {//校对密码
                return new SimpleAuthenticationInfo(user.getUserName(), user.getUserPwd(), ByteSource.Util.bytes(user.getSalt()), this.getName());
            }
        }
        return null;
    }
}
