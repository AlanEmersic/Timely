package com.timely.timely.service.impl;

import com.timely.timely.model.Project;
import com.timely.timely.repository.ProjectRepository;
import com.timely.timely.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project get(final Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project getByName(String name) {
        return projectRepository.findByNameIgnoreCase(name).orElse(null);
    }

    @Override
    public Project getByIdAndName(Long id, String name) {
        return projectRepository.findByIdAndNameIgnoreCase(id, name).orElse(null);
    }

    @Override
    public Set<Project> getAll() {
        return new HashSet<>(projectRepository.findAll());
    }

    @Override
    public void save(final Project project) {
        projectRepository.save(project);
    }

    @Override
    public void delete(final Project project) {
        projectRepository.delete(project);
    }
}
