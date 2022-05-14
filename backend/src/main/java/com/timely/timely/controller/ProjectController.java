package com.timely.timely.controller;

import com.timely.timely.dto.ProjectDto;
import com.timely.timely.facade.ProjectFacade;
import com.timely.timely.form.ProjectForm;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectFacade projectFacade;

    public ProjectController(ProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }

    @GetMapping("/{id}")
    public ProjectDto getProject(@PathVariable final Long id) {
        return projectFacade.get(id);
    }

    @GetMapping
    public Set<ProjectDto> getAllProjects() {
        return projectFacade.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@Valid @RequestBody final ProjectForm projectForm) {
        projectFacade.create(projectForm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable final Long id) {
        projectFacade.delete(id);
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProject(@PathVariable final Long id, @Valid @RequestBody final ProjectForm projectForm) {
        projectFacade.update(id, projectForm);
    }
}
