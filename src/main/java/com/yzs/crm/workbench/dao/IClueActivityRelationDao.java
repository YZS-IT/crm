package com.yzs.crm.workbench.dao;

import com.yzs.crm.workbench.pojo.ClueActivityRelation;

import java.util.List;

public interface IClueActivityRelationDao {
    List<ClueActivityRelation> findByClueId(String clueId);
    boolean insert(ClueActivityRelation relation);
    boolean delete(String id);
}
