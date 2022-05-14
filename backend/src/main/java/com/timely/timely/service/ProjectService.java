package com.timely.timely.service;

import com.timely.timely.model.Project;
import org.springframework.data.domain.Page;

public interface ProjectService {
    Project get(final Long id);

    Project getByName(String name);

    Page<Project> getAll(Integer pageSize, Integer pageNumber);

    void save(final Project project);

    void delete(final Project project);
}
