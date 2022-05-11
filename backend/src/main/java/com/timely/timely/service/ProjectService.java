package com.timely.timely.service;

import com.timely.timely.model.Project;

import java.util.Set;

public interface ProjectService {
    Project get(final Long id);

    Project getByName(String name);

    Project getByIdAndName(Long id, String name);

    Set<Project> getAll();

    void save(final Project project);

    void delete(final Project project);
}
