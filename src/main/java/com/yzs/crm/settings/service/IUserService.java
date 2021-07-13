package com.yzs.crm.settings.service;

import com.yzs.crm.settings.pojo.User;

public interface IUserService {
    User login(String act, String pwd);
}
