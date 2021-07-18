package com.yzs.crm.settings.dao;

import com.yzs.crm.settings.pojo.DicValue;

import java.util.List;

public interface IDicValueDao {
    List<DicValue> findByCode(String code);
    boolean insert(DicValue dicValue);
}
