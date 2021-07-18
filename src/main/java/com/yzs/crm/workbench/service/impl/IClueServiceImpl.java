package com.yzs.crm.workbench.service.impl;

import com.yzs.crm.workbench.bo.ClueValue;
import com.yzs.crm.workbench.dao.IClueDao;
import com.yzs.crm.workbench.pojo.Clue;
import com.yzs.crm.workbench.service.IClueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IClueServiceImpl implements IClueService {

    @Resource
    private IClueDao clueDao;

    @Override
    public boolean save(Clue clue) {
        return clueDao.insert(clue);
    }

    @Override
    public List<Clue> queryClue(ClueValue clueValue) {
        return clueDao.findAll(clueValue);
    }

    @Override
    public Clue detail(String id) {
        return clueDao.getById(id);
    }
}
