import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ApartmentComplex } from './models/apartment-complex.model';
import { Apartment } from './models/apartment.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private baseUrl = 'http://localhost:5432/api'; // Base URL for your API

  constructor(private http: HttpClient) { }

  getApartments(): Observable<Apartment[]> {
    return this.http.get<Apartment[]>(`${this.baseUrl}/apartments`)
      .pipe(
        catchError(this.handleError<Apartment[]>('getApartments', []))
      );
  }

  getApartmentComplexes(): Observable<ApartmentComplex[]> {
    return this.http.get<ApartmentComplex[]>(`${this.baseUrl}/apartment-complexes`)
      .pipe(
        catchError(this.handleError<ApartmentComplex[]>('getApartmentComplexes', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // Log the error to the console
      return of(result as T);
    };
  }
}
