package com.timely.timely.facade.impl;

import com.timely.timely.dto.ProjectDto;
import com.timely.timely.exception.ProjectNotFoundException;
import com.timely.timely.facade.ProjectFacade;
import com.timely.timely.form.ProjectForm;
import com.timely.timely.mapper.ProjectDtoMapper;
import com.timely.timely.mapper.ProjectFormMapper;
import com.timely.timely.model.Project;
import com.timely.timely.service.ProjectService;
import com.timely.timely.validator.ProjectFormValidator;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProjectFacadeImpl implements ProjectFacade {

    private final ProjectDtoMapper projectDtoMapper;
    private final ProjectFormMapper projectFormMapper;
    private final ProjectService projectService;
    private final ProjectFormValidator projectFormValidator;

    private static final String PROJECT_NOT_FOUND_TEXT = "Project not found with id ";

    public ProjectFacadeImpl(ProjectDtoMapper projectDtoMapper, ProjectFormMapper projectFormMapper, ProjectService projectService, ProjectFormValidator projectFormValidator) {
        this.projectDtoMapper = projectDtoMapper;
        this.projectFormMapper = projectFormMapper;
        this.projectService = projectService;
        this.projectFormValidator = projectFormValidator;
    }

    @Override
    public ProjectDto get(Long id) {
        return Optional.ofNullable(projectDtoMapper.map(projectService.get(id))).orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_TEXT + id));
    }

    @Override
    public Set<ProjectDto> getAll() {
        return projectService.getAll().stream().map(projectDtoMapper::map).collect(Collectors.toSet());
    }

    @Override
    public void create(ProjectForm projectForm) {
        projectFormValidator.validateCreate(projectForm);
        projectService.save(projectFormMapper.map(projectForm));
    }

    @Override
    public void delete(Long id) {
        projectService.delete(Optional.ofNullable(projectService.get(id)).orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_TEXT + id)));
    }

    @Override
    public void update(Long id, ProjectForm projectForm) {
        Project project = Optional.ofNullable(projectService.get(id)).orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_TEXT + id));
        projectFormValidator.validateUpdate(project, projectForm);
        projectService.save(projectFormMapper.mapUpdate(project, projectForm));
    }
}
