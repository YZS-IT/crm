package com.yzs.crm.workbench.dao;

import com.yzs.crm.workbench.pojo.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IActivityDao {

    List<Activity> findAll(@Param("owner") String owner, @Param("name") String name, @Param("startDate") String startDate, @Param("endDate") String endDate);

    Activity findByOwner(String owner);

    Activity findById(String id);
    Activity detail(String id);

    boolean insert(Activity activity);
    boolean update(Activity activity);
    boolean delete(List<String> ids);

    List<Activity> findLikeNameNotInRelation(@Param("name") String name, @Param("clueId") String clueId);
}
