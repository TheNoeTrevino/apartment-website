import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private hardcodedUsers = [
    { email: 'admin123@email.com', password: 'admin123', role: 'ADMIN' },
    { email: 'tenant123@email.com', password: 'tenant123', role: 'TENANT' }
  ];

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    const user = this.hardcodedUsers.find(u => u.email === email && u.password === password);
    if (user) {
      localStorage.setItem('token', 'dummy-token'); // Store a dummy token
      localStorage.setItem('role', user.role); // Store the user's role
      return of({ success: true, user });
    } else {
      return of({ success: false, error: 'No user found' });
    }
  }

  redirectUser(role: string) {
    if (role === 'ADMIN') {
      window.location.href = '/admin-dashboard';
    } else if (role === 'TENANT') {
      window.location.href = '/tenant-dashboard';
    }
  }
}
