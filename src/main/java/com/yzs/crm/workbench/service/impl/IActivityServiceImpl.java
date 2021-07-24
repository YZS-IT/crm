package com.yzs.crm.workbench.service.impl;

import com.yzs.crm.workbench.dao.IActivityDao;
import com.yzs.crm.workbench.dao.IActivityRemarkDao;
import com.yzs.crm.workbench.pojo.Activity;
import com.yzs.crm.workbench.pojo.ActivityRemark;
import com.yzs.crm.workbench.service.IActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IActivityServiceImpl implements IActivityService {

    @Resource
    private IActivityDao activityDao;

    @Resource
    private IActivityRemarkDao activityRemarkDao;

    @Override
    public List<Activity> findAll(String owner, String name,String startDate,String endDate) {
        return activityDao.findAll(owner,name,startDate,endDate);
    }

    @Override
    public Activity findByOwner(String owner) {
        return activityDao.findByOwner(owner);
    }

    public Activity getActivity(String id) {
        return activityDao.findById(id);
    }

    @Override
    public Activity detail(String id) {
        return activityDao.detail(id);
    }

    @Override
    public boolean save(Activity activity) {
        return activityDao.insert(activity);
    }
    public boolean update(Activity activity) {
        return activityDao.update(activity);
    }
    public boolean delete(List<String> ids) {
        //避免 误操作导致删除整张表.
        if(ids == null || ids.size()==0) return false;
        return activityDao.delete(ids);
    }


    @Override
    public ActivityRemark findRemarkById(String id) {
        return activityRemarkDao.findById(id);
    }

    @Override
    public List<ActivityRemark> getRemarkList(String activityId) {
        return activityRemarkDao.findByActivityId(activityId);
    }

    @Override
    public boolean insertRemark(ActivityRemark activityRemark) {
        return activityRemarkDao.insert(activityRemark);
    }

    @Override
    public boolean deleteRemark(String id) {
        return activityRemarkDao.delete(id);
    }

    @Override
    public boolean updateRemark(ActivityRemark activityRemark) {
        return activityRemarkDao.update(activityRemark);
    }


}
