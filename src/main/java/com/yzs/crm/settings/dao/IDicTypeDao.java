package com.yzs.crm.settings.dao;

import com.yzs.crm.settings.pojo.DicType;

import java.util.List;

public interface IDicTypeDao {
    List<DicType> findAll();
    DicType getTypeById(String id);

    boolean insert(DicType dicType);

    boolean delete(List<String> ids);

    boolean update(DicType dicType);

}
