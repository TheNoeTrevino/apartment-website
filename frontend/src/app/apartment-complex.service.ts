
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ApartmentComplex } from './models/apartment-complex.model';

@Injectable({
  providedIn: 'root'
})
export class ApartmentComplexService {
  private baseUrl = '//localhost:5432/apartment-project/apartment-complex';

  constructor(private http: HttpClient) { }

  getApartmentComplexes(): Observable<ApartmentComplex[]> {
    return this.http.get<ApartmentComplex[]>(this.baseUrl).pipe(
      catchError(this.handleError<ApartmentComplex[]>('getApartmentComplexes', []))
    );
  }

  getApartmentComplexById(id: number): Observable<ApartmentComplex> {
    return this.http.get<ApartmentComplex>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError<ApartmentComplex>('getApartmentComplexById'))
    );
  }

  createApartmentComplex(apartmentComplex: ApartmentComplex): Observable<ApartmentComplex> {
    return this.http.post<ApartmentComplex>(this.baseUrl, apartmentComplex).pipe(
      catchError(this.handleError<ApartmentComplex>('createApartmentComplex'))
    );
  }

  updateApartmentComplex(id: number, apartmentComplex: ApartmentComplex): Observable<ApartmentComplex> {
    return this.http.patch<ApartmentComplex>(`${this.baseUrl}/${id}`, apartmentComplex).pipe(
      catchError(this.handleError<ApartmentComplex>('updateApartmentComplex'))
    );
  }

  deleteApartmentComplex(id: number): Observable<ApartmentComplex> {
    return this.http.delete<ApartmentComplex>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError<ApartmentComplex>('deleteApartmentComplex'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}

