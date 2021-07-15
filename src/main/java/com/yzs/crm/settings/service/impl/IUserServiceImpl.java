package com.yzs.crm.settings.service.impl;

import com.yzs.crm.settings.dao.IUserDao;
import com.yzs.crm.settings.exception.LoginException;
import com.yzs.crm.settings.pojo.User;
import com.yzs.crm.settings.service.IUserService;
import com.yzs.crm.util.DateTimeUtil;
import com.yzs.crm.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public User login(String loginAct, String loginPwd, String loginIp) throws LoginException {
        User user = userDao.findByActAndPwd(loginAct,MD5Util.getMD5(loginPwd));
        if(user == null){
            throw new LoginException("账号或密码错误!");
        }else if(DateTimeUtil.GetCompareRes(user.getExpireTime(),DateTimeUtil.getSysTime())<0){
            throw new LoginException("账号已失效!");
        }else if(!"0".equals(user.getLockState())){
            throw new LoginException("账号被封禁中,请联系管理员!");
        }else if(!(user.getAllowIps()==null||"".equals(user.getAllowIps()))&&!user.getAllowIps().contains(loginIp)){
            throw new LoginException("您的IP地址不允许登录!");
        }
        return user;
    }

    @Override
    public List<User> getUserList() {
        return userDao.findAll();
    }
}
