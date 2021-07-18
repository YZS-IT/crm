package com.yzs.crm.workbench.dao;

import com.yzs.crm.workbench.pojo.ActivityRemark;

import java.util.List;

public interface IActivityRemarkDao {

    boolean insert(ActivityRemark activityRemark);
    boolean delete(String id);
    boolean update(ActivityRemark activityRemark);

    List<ActivityRemark> findAll();
    ActivityRemark findById(String id);
    List<ActivityRemark> findByActivityId(String activityId);
}
