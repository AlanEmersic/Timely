import { Component, OnInit } from '@angular/core';
import { Project } from '../project.model';
import { ProjectService } from '../project.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css'],
})
export class ProjectComponent implements OnInit {
  projects: Project[] = [];
  projectFIlterText!: string;

  constructor(private projectService: ProjectService) {}

  ngOnInit(): void {
    this.getProjects();
  }

  getProjects() {
    this.projectService.getProjects().subscribe((projects) => {
      this.projects = projects;
    });
  }

  addProject(form: any) {
    const project = {
      name: form.value.name,
      startTime: form.value.startTime,
      endTime: form.value.endTime,
    };

    this.projectService.addProject(project).subscribe((project) => {
      this.projects.push(project);
      form.reset();
      this.getProjects();
    });
  }

  updateProject(id: number, form: any) {
    const project = {
      name: form.value.name,
      startTime: form.value.startTime,
      endTime: form.value.endTime,
    };

    this.projectService.updateProject(id, project).subscribe((project) => {
      form.reset();
      this.getProjects();
    });
  }

  deleteProject(project: Project) {
    this.projects = this.projects?.filter((p) => p !== project);
    this.projectService.deleteProduct(project).subscribe();
  }
}
