package com.yzs.crm.settings.service;

import com.yzs.crm.settings.exception.LoginException;
import com.yzs.crm.settings.pojo.User;

public interface IUserService {
    User login(String loginAct, String loginPwd, String loginIp) throws LoginException;
}
