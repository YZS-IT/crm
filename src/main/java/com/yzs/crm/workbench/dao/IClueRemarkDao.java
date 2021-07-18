package com.yzs.crm.workbench.dao;

import com.yzs.crm.workbench.pojo.ClueRemark;

import java.util.List;

public interface IClueRemarkDao {
    boolean insert(ClueRemark clueRemark);
    boolean delete(String id);
    boolean update(ClueRemark clueRemark);

    List<ClueRemark> findAll();
    ClueRemark findById(String id);
    List<ClueRemark> findByClueId(String clueId);
}
