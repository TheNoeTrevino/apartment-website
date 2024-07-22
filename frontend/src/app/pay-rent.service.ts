import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PayRentService {
  private rentAmount: number = 500; // Mocked rent amount

  getRentDetails(): Observable<{ rentAmount: number }> {
    // Simulate an API call
    return of({ rentAmount: this.rentAmount });
  }

  payRent(paymentAmount: number): Observable<any> {
    // Simulate a payment processing
    this.rentAmount -= paymentAmount;
    return of({ success: true });
  }
}

