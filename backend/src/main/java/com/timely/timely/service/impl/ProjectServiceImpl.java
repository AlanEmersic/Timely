package com.timely.timely.service.impl;

import com.timely.timely.model.Project;
import com.timely.timely.repository.ProjectRepository;
import com.timely.timely.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Page<Project> getAll(Integer pageSize, Integer pageNumber) {
        return projectRepository.findAll(PageRequest.of(pageNumber, pageSize));
//        return new HashSet<>(projectRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent());
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
