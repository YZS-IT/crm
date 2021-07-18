package com.yzs.crm.settings.dao;

import com.yzs.crm.settings.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {

    boolean insert(User user);

    boolean delete(int id);

    boolean update(User user);

    List<User> findAll();

    User findByActAndPwd(@Param("loginAct") String loginAct, @Param("loginPwd") String loginPwd);
    User findById(String id);

}
