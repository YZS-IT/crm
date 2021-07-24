package com.yzs.crm.workbench.service.impl;

import com.yzs.crm.workbench.bo.ClueValue;
import com.yzs.crm.workbench.dao.IActivityDao;
import com.yzs.crm.workbench.dao.IClueActivityRelationDao;
import com.yzs.crm.workbench.dao.IClueDao;
import com.yzs.crm.workbench.pojo.Activity;
import com.yzs.crm.workbench.pojo.Clue;
import com.yzs.crm.workbench.pojo.ClueActivityRelation;
import com.yzs.crm.workbench.service.IClueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IClueServiceImpl implements IClueService {

    @Resource
    private IClueDao clueDao;

    @Resource
    private IClueActivityRelationDao clueActivityRelationDao;

    @Resource
    private IActivityDao activityDao;

    @Override
    public boolean save(Clue clue) {
        return clueDao.insert(clue);
    }

    @Override
    public boolean delete(List<String> ids) {
        if(ids == null || ids.size() == 0) return false;
        return clueDao.delete(ids);
    }

    @Override
    public boolean update(Clue clue) {
        return clueDao.update(clue);
    }

    @Override
    public List<Clue> queryClue(ClueValue clueValue) {
        return clueDao.findAll(clueValue);
    }

    @Override
    public Clue detail(String id) {
        return clueDao.getById(id);
    }

    @Override
    public Clue findById(String id) {
        return clueDao.findById(id);
    }

    @Override
    public List<ClueActivityRelation> getClueActivityRelationList(String id) {
        return clueActivityRelationDao.findByClueId(id);
    }

    @Override
    public boolean saveClueActivityRelation(ClueActivityRelation relation) {
        return clueActivityRelationDao.insert(relation);
    }

    @Override
    public boolean deleteClueActivityRelation(String id) {
        return clueActivityRelationDao.delete(id);
    }

    @Override
    public List<Activity> getActivityLikeNameAndNotBindedByClueId(String name, String clueId) {
        return activityDao.findLikeNameNotInRelation(name,clueId);
    }
}
