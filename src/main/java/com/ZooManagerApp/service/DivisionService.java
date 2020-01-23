package com.ZooManagerApp.service;

import com.ZooManagerApp.model.Division;

import java.util.List;

public interface DivisionService {
    List<Division> getAllDivisions();
    Division getDivision(long divisionId);
    Division updateDivision(Division division);
    Division addDivision(Division division);
    void removeDivision(long divisionId);
}
