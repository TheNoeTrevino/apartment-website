import { Observable, of } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

export function handleError<T>(operation = 'operation', result?: T) {
  return (error: HttpErrorResponse): Observable<T> => {
    console.error(`${operation} failed: ${error.message}`);
    return of(result as T);
  };
}

