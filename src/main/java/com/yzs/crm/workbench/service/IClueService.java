package com.yzs.crm.workbench.service;

import com.yzs.crm.workbench.bo.ClueValue;
import com.yzs.crm.workbench.bo.TranValue;
import com.yzs.crm.workbench.pojo.Activity;
import com.yzs.crm.workbench.pojo.Clue;
import com.yzs.crm.workbench.pojo.ClueActivityRelation;

import java.util.List;

public interface IClueService {
    boolean save(Clue clue);
    boolean delete(List<String> ids);
    boolean update(Clue clue);

    List<Clue> queryClue(ClueValue clueValue);
    Clue detail(String id);
    Clue findById(String id);

    List<ClueActivityRelation> getClueActivityRelationList(String id);
    boolean saveClueActivityRelation(ClueActivityRelation relation);
    boolean deleteClueActivityRelation(String id);
    List<Activity> getActivityLikeNameAndNotBoundByClueId(String name, String clueId);

    boolean convertConfirm(String clueId, TranValue tranValue, String createBy);

}
