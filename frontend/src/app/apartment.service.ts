
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ApartmentDTO } from './models/apartment.dto';

@Injectable({
  providedIn: 'root'
})
export class ApartmentService {
  private baseUrl = 'http://localhost:8080/api/apartments';

  constructor(private http: HttpClient) { }

  getApartments(page: number, size: number): Observable<any> {
    let params = new HttpParams();
    params = params.append('page', page.toString());
    params = params.append('size', size.toString());

    return this.http.get<any>(this.baseUrl, { params }).pipe(
      catchError(this.handleError<any>('getApartments', []))
    );
  }

  getApartmentById(id: number): Observable<ApartmentDTO> {
    return this.http.get<ApartmentDTO>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError<ApartmentDTO>('getApartmentById'))
    );
  }

  createApartment(apartment: ApartmentDTO): Observable<ApartmentDTO> {
    return this.http.post<ApartmentDTO>(this.baseUrl, apartment).pipe(
      catchError(this.handleError<ApartmentDTO>('createApartment'))
    );
  }

  updateApartment(id: number, apartment: ApartmentDTO): Observable<ApartmentDTO> {
    return this.http.put<ApartmentDTO>(`${this.baseUrl}/${id}`, apartment).pipe(
      catchError(this.handleError<ApartmentDTO>('updateApartment'))
    );
  }

  deleteApartment(id: number): Observable<ApartmentDTO> {
    return this.http.delete<ApartmentDTO>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError<ApartmentDTO>('deleteApartment'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
