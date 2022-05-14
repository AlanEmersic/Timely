package com.timely.timely.facade;

import com.timely.timely.dto.ProjectDto;
import com.timely.timely.form.ProjectForm;
import org.springframework.data.domain.Page;

public interface ProjectFacade {
    ProjectDto get(Long id);

    Page<ProjectDto> getAll(Integer pageSize, Integer pageNumber);

    void create(ProjectForm projectForm);

    void delete(Long id);

    void update(Long id, ProjectForm projectForm);
}
