package com.laba2.dao;

import com.laba2.models.Project;

import java.util.List;

public interface DaoProject {
    List<Project> selectAllProject();

    List<Project> findByCustomerId(int id);

    List<Project> findByLocationId(int id);
    List<Project> findByRequirementsId(int id);

    Project findById(int id);

    void editProject(Project project);

    void deleteProject(int id);

    void createProject(Project project);
}
