package com.cao.xps.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.xps.common.adminVo.UserParams;
import com.cao.xps.common.shiro.SaltUtil;
import com.cao.xps.common.vo.DataTablesResult;
import com.cao.xps.service.user.entity.User;
import com.cao.xps.service.user.mapper.UserMapper;
import com.cao.xps.service.user.service.UserService;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-07-16
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public DataTablesResult<User> dataLists(UserParams userParams){
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>().like("user_name",userParams.getKeyword());
        List<User> users = userMapper.selectList(queryWrapper);
        DataTablesResult<User> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(users);
        dataTablesResult.setRecordsFiltered(users.size());
        dataTablesResult.setRecordsTotal(users.size());
        return dataTablesResult;
    }

    @Override
    public Integer delUser(UserParams userParams){
        return userMapper.deleteById(userParams.getUserId());
    }
    @Override
    public User getUserByUserId(UserParams userParams){
        return userMapper.selectById(userParams.getUserId());
    }
    @Override
    public Integer saveUser(User user){
        Integer result = 0;
        if(user.getUserId()!=null){
            result=userMapper.updateById(user);
        }else{
            //1.获取随机盐
            String salt = SaltUtil.getSalt(8);
            //2.将随机盐保存到数据
            user.setSalt(salt);
            //3.明文密码进行md5 + salt + hash散列
            Md5Hash md5 = new Md5Hash(user.getUserPwd(),salt,1024);
            user.setUserPwd(md5.toHex());
            user.setCreateTime(LocalDateTime.now());
            result=userMapper.insert(user);
        }
        return result;
    }
}
