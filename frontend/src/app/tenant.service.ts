import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Tenant } from './models/tenant.model';

@Injectable({
  providedIn: 'root'
})
export class TenantService {
  private baseUrl = '//localhost:5432/apartment-project/tenant';

  constructor(private http: HttpClient) { }

  getTenants(): Observable<Tenant[]> {
    return this.http.get<Tenant[]>(this.baseUrl).pipe(
      catchError(this.handleError<Tenant[]>('getTenants', []))
    );
  }

  getTenantById(id: number): Observable<Tenant> {
    return this.http.get<Tenant>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError<Tenant>('getTenantById'))
    );
  }

  createTenant(tenant: Tenant): Observable<Tenant> {
    return this.http.post<Tenant>(this.baseUrl, tenant).pipe(
      catchError(this.handleError<Tenant>('createTenant'))
    );
  }

  updateTenant(id: number, tenant: Tenant): Observable<Tenant> {
    return this.http.patch<Tenant>(`${this.baseUrl}/${id}`, tenant).pipe(
      catchError(this.handleError<Tenant>('updateTenant'))
    );
  }

  deleteTenant(id: number): Observable<Tenant> {
    return this.http.delete<Tenant>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError<Tenant>('deleteTenant'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}

