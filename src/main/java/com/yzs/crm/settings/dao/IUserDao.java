package com.yzs.crm.settings.dao;

import com.yzs.crm.settings.pojo.User;

import java.util.List;

public interface IUserDao {

    boolean insert(User user);
    boolean delete(int id);
    boolean update(User user);

    List<User> findAll();

}
