package com.timely.timely.facade;

import com.timely.timely.dto.ProjectDto;
import com.timely.timely.form.ProjectForm;

import java.util.Set;

public interface ProjectFacade {
    ProjectDto get(Long id);

    Set<ProjectDto> getAll();

    void create(ProjectForm projectForm);

    void delete(Long id);

    void update(Long id, ProjectForm projectForm);
}
