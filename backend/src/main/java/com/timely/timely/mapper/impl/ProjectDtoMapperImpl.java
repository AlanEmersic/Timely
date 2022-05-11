package com.timely.timely.mapper.impl;

import com.timely.timely.dto.ProjectDto;
import com.timely.timely.mapper.ProjectDtoMapper;
import com.timely.timely.model.Project;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProjectDtoMapperImpl implements ProjectDtoMapper {
    @Override
    public ProjectDto map(Project project) {

        if (Objects.isNull(project))
            return null;

        return new ProjectDto(project.getId(), project.getName(), project.getStartTime(), project.getEndTime(), project.getDuration());
    }
}
