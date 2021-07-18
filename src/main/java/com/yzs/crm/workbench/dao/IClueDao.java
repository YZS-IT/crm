package com.yzs.crm.workbench.dao;

import com.yzs.crm.workbench.bo.ClueValue;
import com.yzs.crm.workbench.pojo.Clue;

import java.util.List;

public interface IClueDao {
    boolean insert(Clue clue);
    boolean delete(List<String> ids);
    boolean update(Clue clue);

    List<Clue> findAll(ClueValue clueValue);
    Clue findById(String id);
    Clue getById(String id);

}
