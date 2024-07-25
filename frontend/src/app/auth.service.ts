import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map, switchMap, filter } from 'rxjs/operators';
import { jwtDecode, JwtPayload } from 'jwt-decode';
import { isPlatformBrowser } from '@angular/common';
import { Router, UrlTree } from '@angular/router';

export interface UserData {
  token: string;
  id: string;
}

export const USER_STORAGE_KEY = 'APP_TOKEN';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private user: BehaviorSubject<UserData | null | undefined> = new BehaviorSubject<UserData | null | undefined>(undefined);

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: any
  ) {
    this.loadUser();
  }

  loadUser() {
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem(USER_STORAGE_KEY);

      if (token) {
        const decoded: JwtPayload = jwtDecode<JwtPayload>(token);

        const userData: UserData = {
          token: token,
          id: decoded.sub!
        };
        this.user.next(userData);
      } else {
        this.user.next(null);
      }
    }
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post('http://localhost:8080/api/auth/login', { email, password }).pipe(
      map((response: any) => {
        console.log('result: ', response);
        if (isPlatformBrowser(this.platformId)) {
          localStorage.setItem(USER_STORAGE_KEY, response.token);
        }
        const decoded: JwtPayload = jwtDecode<JwtPayload>(response.token);

        const userData: UserData = {
          token: response.token,
          id: decoded.sub!
        };
        this.user.next(userData);

        return userData;
      })
    );
  }

  register(email: string, password: string): Observable<any> {
    return this.http.post('http://localhost:8080/api/auth/register', { email, password }).pipe(
      switchMap((response: any) => {
        return this.login(email, password);
      })
    );
  }

  signOut() {
    localStorage.removeItem(USER_STORAGE_KEY);
    this.user.next(null);
  }

  getCurrentUser() {
    return this.user.asObservable();
  }

  getCurrentUserId() {
    return this.user.getValue()!.id;
  }
}
