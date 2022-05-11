package com.timely.timely.mapper;

import com.timely.timely.form.ProjectForm;
import com.timely.timely.model.Project;

public interface ProjectFormMapper {
    Project map(ProjectForm form);

    Project mapUpdate(Project project, ProjectForm form);
}
