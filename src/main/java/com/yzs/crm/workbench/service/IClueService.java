package com.yzs.crm.workbench.service;

import com.yzs.crm.workbench.bo.ClueValue;
import com.yzs.crm.workbench.pojo.Clue;

import java.util.List;

public interface IClueService {
    boolean save(Clue clue);
    List<Clue> queryClue(ClueValue clueValue);
    Clue detail(String id);
}
