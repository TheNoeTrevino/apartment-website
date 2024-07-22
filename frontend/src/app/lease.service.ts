
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Lease } from './models/lease.model';

@Injectable({
  providedIn: 'root'
})
export class LeaseService {
  private baseUrl = '//localhost:5432/apartment-project/leases';

  constructor(private http: HttpClient) { }

  getLeases(): Observable<Lease[]> {
    return this.http.get<Lease[]>(this.baseUrl).pipe(
      catchError(this.handleError<Lease[]>('getLeases', []))
    );
  }

  getLeaseById(id: number): Observable<Lease> {
    return this.http.get<Lease>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError<Lease>('getLeaseById'))
    );
  }

  createLease(lease: Lease): Observable<Lease> {
    return this.http.post<Lease>(this.baseUrl, lease).pipe(
      catchError(this.handleError<Lease>('createLease'))
    );
  }

  updateLease(id: number, lease: Lease): Observable<Lease> {
    return this.http.patch<Lease>(`${this.baseUrl}/${id}`, lease).pipe(
      catchError(this.handleError<Lease>('updateLease'))
    );
  }

  deleteLease(id: number): Observable<Lease> {
    return this.http.delete<Lease>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError<Lease>('deleteLease'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}

