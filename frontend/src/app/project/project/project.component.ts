import { Component, OnInit } from '@angular/core';
import { Project } from '../project.model';
import { ProjectService } from '../project.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { formatDate } from '@angular/common';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css'],
})
export class ProjectComponent implements OnInit {
  projects: Project[] = [];
  projectFilterText!: string;
  isTimerStarted: boolean = false;
  time: number = 0;
  timeDisplay!: string;
  workingProject!: Project;
  isNewProject: boolean = false;
  newProjectName!: string;
  closeResult = '';
  private projectStartDate!: string;
  private projectEndDate!: string;
  private interval!: any;

  constructor(
    private projectService: ProjectService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.getProjects();
  }

  ngOnDestroy() {
    if (this.interval) {
      clearInterval(this.interval);
    }
  }

  getProjects() {
    this.projectService.getProjects().subscribe((projects) => {
      this.projects = projects.sort((a, b) => a.id - b.id);
    });
  }

  addProject() {
    this.isTimerStarted = false;
    this.timeDisplay = '';
    if (this.interval) {
      clearInterval(this.interval);
    }

    this.projectEndDate = formatDate(
      new Date(),
      'yyyy-MM-dd HH:mm:ss',
      'hr-HR'
    );

    const project = {
      name: this.newProjectName,
      startTime: this.projectStartDate,
      endTime: this.projectEndDate,
    };

    this.newProjectName = '';
    this.isNewProject = false;

    this.projectService.addProject(project).subscribe((project) => {
      this.projects.push(project);
      this.getProjects();
    });
  }

  updateProject(project: Project) {
    this.isTimerStarted = false;

    const updatedProject = {
      name: project.name,
      startTime: this.projectStartDate,
      endTime: this.projectEndDate,
    };

    this.projectService
      .updateProject(project.id, updatedProject)
      .subscribe((p) => {
        this.getProjects();
      });
  }

  deleteProject(project: Project) {
    this.projects = this.projects?.filter((p) => p !== project);
    this.projectService.deleteProduct(project).subscribe();
  }

  startTimer(project: any) {
    this.time = 0;
    this.isTimerStarted = true;
    this.workingProject = project;

    if (project == null) {
      this.isNewProject = true;
    }

    this.projectStartDate = formatDate(
      new Date(),
      'yyyy-MM-dd HH:mm:ss',
      'hr-HR'
    );

    this.interval = setInterval(() => {
      this.time++;
      const hours = Math.floor(this.time / 3600);
      const minutes = Math.floor(this.time / 60);
      const seconds = this.time - 3600 * hours - 60 * minutes;
      this.timeDisplay = `${hours}h:${minutes}m:${seconds}s`;
    }, 1000);
  }

  stopTimer() {
    this.isTimerStarted = false;
    this.timeDisplay = '';
    if (this.interval) {
      clearInterval(this.interval);
    }

    this.projectEndDate = formatDate(
      new Date(),
      'yyyy-MM-dd HH:mm:ss',
      'hr-HR'
    );

    this.updateProject(this.workingProject);
  }

  open(content: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  exportExcel(): void {
    let element = document.getElementById('projects');
    const ws: XLSX.WorkSheet = XLSX.utils.table_to_sheet(element);
    var wscols = [{ wch: 30 }, { wch: 30 }, { wch: 30 }, { wch: 30 }];

    ws['!cols'] = wscols;
    ws['!ref'] = ws['!ref']?.replace('E', 'D');

    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

    const fileName = 'Projects.xlsx';
    XLSX.writeFile(wb, fileName);
  }
}
