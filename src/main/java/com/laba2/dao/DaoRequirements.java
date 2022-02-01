package com.laba2.dao;

import com.laba2.models.Requirements;

import java.util.List;

public interface DaoRequirements {

    List<Requirements> selectAllRequirements();

    void deleteRequirements(int id);

    void createRequirements(Requirements requirements);
}
