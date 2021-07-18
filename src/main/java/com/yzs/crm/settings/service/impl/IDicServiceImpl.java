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
}
