package com.yzs.crm.settings.dao;

import com.yzs.crm.settings.pojo.DicValue;

import java.util.List;

public interface IDicValueDao {
    //此方法是用存储数据字典的.
    List<DicValue> findByCode(String code);

    List<DicValue> findAll();
    DicValue getValueById(String id);

    boolean insert(DicValue dicValue);

    boolean delete(List<String> ids);

    boolean update(DicValue dicValue);
}
