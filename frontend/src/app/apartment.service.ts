
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Apartment } from './models/apartment.model';

@Injectable({
  providedIn: 'root'
})
export class ApartmentService {
  private baseUrl = '//localhost:5432/apartment-project/apartment';

  constructor(private http: HttpClient) { }

  getApartments(): Observable<Apartment[]> {
    return this.http.get<Apartment[]>(this.baseUrl).pipe(
      catchError(this.handleError<Apartment[]>('getApartments', []))
    );
  }

  getApartmentById(id: number): Observable<Apartment> {
    return this.http.get<Apartment>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError<Apartment>('getApartmentById'))
    );
  }

  createApartment(apartment: Apartment): Observable<Apartment> {
    return this.http.post<Apartment>(this.baseUrl, apartment).pipe(
      catchError(this.handleError<Apartment>('createApartment'))
    );
  }

  updateApartment(id: number, apartment: Apartment): Observable<Apartment> {
    return this.http.patch<Apartment>(`${this.baseUrl}/${id}`, apartment).pipe(
      catchError(this.handleError<Apartment>('updateApartment'))
    );
  }

  deleteApartment(id: number): Observable<Apartment> {
    return this.http.delete<Apartment>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError<Apartment>('deleteApartment'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}

