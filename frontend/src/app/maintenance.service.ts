
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MaintenanceRequest } from './models/maintenance-request.model';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MaintenanceService {
  private apiUrl = 'http://localhost:5432/api/maintenance-requests'; // Update with your actual backend API URL

  constructor(private http: HttpClient) { }

  getMaintenanceRequests(): Observable<MaintenanceRequest[]> {
    return this.http.get<MaintenanceRequest[]>(this.apiUrl);
  }

  getMaintenanceRequest(id: number): Observable<MaintenanceRequest> {
    return this.http.get<MaintenanceRequest>(`${this.apiUrl}/${id}`);
  }

  createMaintenanceRequest(request: MaintenanceRequest): Observable<MaintenanceRequest> {
    return this.http.post<MaintenanceRequest>(this.apiUrl, request)
      .pipe(
        catchError(this.handleError<MaintenanceRequest>('createMaintenanceRequest'))
      );
  }

  updateMaintenanceRequest(id: number, request: MaintenanceRequest): Observable<MaintenanceRequest> {
    return this.http.put<MaintenanceRequest>(`${this.apiUrl}/${id}`, request)
      .pipe(
        catchError(this.handleError<MaintenanceRequest>('updateMaintenanceRequest'))
      );
  }

  deleteMaintenanceRequest(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`)
      .pipe(
        catchError(this.handleError<void>('deleteMaintenanceRequest'))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}

