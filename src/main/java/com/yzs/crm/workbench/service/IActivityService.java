package com.yzs.crm.workbench.service;

import com.yzs.crm.workbench.pojo.Activity;
import com.yzs.crm.workbench.pojo.ActivityRemark;

import java.util.List;

public interface IActivityService {
    List<Activity> findAll(String owner,String name,String startDate,String endDate);
    Activity findByOwner(String owner);
    Activity getActivity(String id);
    Activity detail(String id);
    boolean save(Activity activity);
    boolean update(Activity activity);
    boolean delete(List<String> ids);


    List<ActivityRemark> getRemarkList(String activityId);
    ActivityRemark findRemarkById(String id);
    boolean insertRemark(ActivityRemark activityRemark);
    boolean deleteRemark(String id);
    boolean updateRemark(ActivityRemark activityRemark);
}
