package com.yzs.crm.settings.service;

import com.yzs.crm.settings.pojo.DicType;
import com.yzs.crm.settings.pojo.DicValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IDicService {
    Map<String , List<DicValue> > getAll();
    boolean insert(DicValue dicValue);

    List<DicType> getTypeList();
    List<DicValue> getValueList();

    DicType getTypeById(String id);
    DicValue getValueById(String id);

    boolean saveType(DicType dicType);
    boolean deleteType(List<String> ids);
    boolean updateType(DicType dicType);

    boolean saveValue(DicValue dicValue);
    boolean deleteValue(List<String> ids);
    boolean updateValue(DicValue dicValue);

}
