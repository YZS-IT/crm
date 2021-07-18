package com.yzs.crm.settings.service;

import com.yzs.crm.settings.pojo.DicValue;

import java.util.List;
import java.util.Map;

public interface IDicService {
    Map<String , List<DicValue> > getAll();
    boolean insert(DicValue dicValue);
}
