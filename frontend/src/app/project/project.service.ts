import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Project } from './project.model';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private apiUrl = environment.apiURL + '/projects';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient) {}

  getProjects(pageNumber: number, pageSize: number): Observable<any> {
    const url = `${this.apiUrl}?pageNumber=${pageNumber}&?pageSize=${pageSize}`;
    return this.http.get<any>(url).pipe(
      tap(() => console.log('fetched projects')),
      catchError(this.handleError<any>('getProjects', []))
    );
  }

  getProjectById(id: number): Observable<Project> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Project>(url).pipe(
      tap(() => console.log('fetched project')),
      catchError(this.handleError<Project>('getProjectById'))
    );
  }

  addProject(project: any): Observable<Project> {
    const url = `${this.apiUrl}`;
    return this.http.post<Project>(url, project, this.httpOptions).pipe(
      tap(() => console.log(`added project`)),
      catchError(this.handleError<Project>('addProject'))
    );
  }

  updateProject(id: number, project: any): Observable<Project> {
    const url = `${this.apiUrl}/${id}/update`;
    return this.http.put<Project>(url, project, this.httpOptions).pipe(
      tap(() => console.log(`updated project`)),
      catchError(this.handleError<Project>('updateProject'))
    );
  }

  deleteProduct(project: Project | number): Observable<Project> {
    const id = typeof project === 'number' ? project : project.id;
    const url = `${this.apiUrl}/${id}`;

    return this.http.delete<Project>(url, this.httpOptions).pipe(
      tap(() => console.log(`delete project id=${id}`)),
      catchError(this.handleError<Project>('deleteProduct'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(operation);
      console.error(error);
      return of(result as T);
    };
  }
}
