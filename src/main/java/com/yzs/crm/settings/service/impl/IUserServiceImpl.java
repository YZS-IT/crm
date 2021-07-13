package com.yzs.crm.settings.service.impl;

import com.yzs.crm.settings.dao.IUserDao;
import com.yzs.crm.settings.pojo.User;
import com.yzs.crm.settings.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IUserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public User login(String act, String pwd) {
        return userDao.findByActAndPwd(act,pwd);
    }

}
