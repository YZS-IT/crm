package com.yzs.crm.settings.service.impl;

import com.yzs.crm.settings.dao.IDicTypeDao;
import com.yzs.crm.settings.dao.IDicValueDao;
import com.yzs.crm.settings.pojo.DicType;
import com.yzs.crm.settings.pojo.DicValue;
import com.yzs.crm.settings.service.IDicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IDicServiceImpl implements IDicService {

    @Resource
    private IDicTypeDao dicTypeDao;

    @Resource
    private IDicValueDao dicValueDao;

    @Override
    public Map<String, List<DicValue>> getAll() {
        Map<String ,List<DicValue>> map = new HashMap<>();
        List<DicType> dicTypeList = dicTypeDao.findAll();
        for (DicType dicType : dicTypeList) {
            String code = dicType.getCode();
            List<DicValue> dicValueList = dicValueDao.findByCode(code);
            map.put(code,dicValueList);
        }
        return map;
    }

    @Override
    public boolean insert(DicValue dicValue) {
        return dicValueDao.insert(dicValue);
    }

    @Override
    public List<DicType> getTypeList() {
        return dicTypeDao.findAll();
    }

    @Override
    public List<DicValue> getValueList() {
        return dicValueDao.findAll();
    }

    @Override
    public DicType getTypeById(String id) {
        return dicTypeDao.getTypeById(id);
    }

    @Override
    public DicValue getValueById(String id) {
        return dicValueDao.getValueById(id);
    }

    @Override
    public boolean saveType(DicType dicType) {
        return dicTypeDao.insert(dicType);
    }


    @Override
    public boolean deleteType(List<String> ids) {
        //防止删除表中全部数据
        if(ids == null || ids.size() == 0){
            return false;
        }
        return dicTypeDao.delete(ids);
    }

    @Override
    public boolean updateType(DicType dicType) {
        return dicTypeDao.update(dicType);
    }

    @Override
    public boolean saveValue(DicValue dicValue) {
        return dicValueDao.insert(dicValue);
    }

    @Override
    public boolean deleteValue(List<String> ids) {
        //防止删除表中全部数据
        if(ids == null || ids.size() == 0){
            return false;
        }
        return dicValueDao.delete(ids);
    }

    @Override
    public boolean updateValue(DicValue dicValue) {
        return dicValueDao.update(dicValue);
    }
}
