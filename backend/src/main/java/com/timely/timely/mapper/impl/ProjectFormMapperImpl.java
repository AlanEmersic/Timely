package com.timely.timely.mapper.impl;

import com.timely.timely.form.ProjectForm;
import com.timely.timely.mapper.ProjectFormMapper;
import com.timely.timely.model.Project;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
public class ProjectFormMapperImpl implements ProjectFormMapper {
    @Override
    public Project map(ProjectForm form) {
        if (Objects.isNull(form))
            return null;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(form.getStartTime(), dateTimeFormatter);
        LocalDateTime endTime = LocalDateTime.parse(form.getEndTime(), dateTimeFormatter);

        Project project = new Project();
        project.setName(form.getName());
        project.setStartTime(startTime);
        project.setEndTime(endTime);
        project.setDuration(Duration.between(startTime, endTime));

        return project;
    }

    @Override
    public Project mapUpdate(Project project, ProjectForm form) {
        if (Objects.isNull(form))
            return null;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(form.getStartTime(), dateTimeFormatter);
        LocalDateTime endTime = LocalDateTime.parse(form.getEndTime(), dateTimeFormatter);

        project.setName(form.getName());
        project.setStartTime(startTime);
        project.setEndTime(endTime);
        project.setDuration(Duration.between(startTime, endTime));

        return project;
    }
}
