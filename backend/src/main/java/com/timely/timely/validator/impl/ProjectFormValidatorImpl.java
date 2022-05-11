package com.timely.timely.validator.impl;

import com.timely.timely.exception.ProjectValidationException;
import com.timely.timely.form.ProjectForm;
import com.timely.timely.model.Project;
import com.timely.timely.service.ProjectService;
import com.timely.timely.validator.ProjectFormValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProjectFormValidatorImpl implements ProjectFormValidator {
    private final ProjectService projectService;

    public ProjectFormValidatorImpl(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void validateCreate(ProjectForm form) {
        boolean isProjectWithSameNameCreated = projectService.getByName(form.getName()) != null;

        if (isProjectWithSameNameCreated)
            throw new ProjectValidationException("Project with same name exists!");
    }

    @Override
    public void validateUpdate(Project project, ProjectForm form) {
        Project projectForm = projectService.getByName(form.getName());

        if (projectForm != null && !Objects.equals(projectForm.getId(), project.getId()))
            throw new ProjectValidationException("Project with same name exists!");
    }
}
