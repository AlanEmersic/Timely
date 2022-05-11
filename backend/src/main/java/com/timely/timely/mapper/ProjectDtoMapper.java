package com.timely.timely.mapper;

import com.timely.timely.dto.ProjectDto;
import com.timely.timely.model.Project;

public interface ProjectDtoMapper {
    ProjectDto map(Project project);
}
