package com.timely.timely.validator;

import com.timely.timely.form.ProjectForm;
import com.timely.timely.model.Project;

public interface ProjectFormValidator {
    void validateCreate(ProjectForm form);

    void validateUpdate(Project project, ProjectForm form);
}
