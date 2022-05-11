import { Pipe, PipeTransform } from '@angular/core';
import { Project } from './project.model';

@Pipe({
  name: 'project',
})
export class ProjectPipe implements PipeTransform {
  transform(projects: Project[], text: string): Project[] {
    if (!projects || !text) {
      return projects;
    }

    return projects.filter((project) =>
      project.name.toLowerCase().includes(text.toLowerCase())
    );
  }
}
