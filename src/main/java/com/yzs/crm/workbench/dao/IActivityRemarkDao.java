package com.yzs.crm.workbench.dao;

import com.yzs.crm.workbench.pojo.ActivityRemark;

import java.util.List;

public interface IActivityRemarkDao {
    List<ActivityRemark> findAll();
    ActivityRemark findByActivityId(String activityId);
}
