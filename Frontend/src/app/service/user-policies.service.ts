import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserPolicies } from '../models/userpolicies.model';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserPoliciesService {

  private baseUrl = 'http://localhost:8080/api/v1/userPolicies/';

  constructor(private http: HttpClient) { }

  registerUserPolicies(userPolicies: UserPolicies): Observable<any> {
    return this.http.post<any>(this.baseUrl + 'register', userPolicies, { responseType: 'json' })
      .pipe(
        catchError(error => throwError(error))
      );
  }

  getUserPoliciesbyUserId(userId: any): Observable<UserPolicies[]> {
    const tokenString = 'Bearer ' + localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': tokenString
    });
    return this.http.get<UserPolicies[]>(this.baseUrl + 'get-by-userId/' + userId, { headers, responseType: 'json' })
      .pipe(
        catchError(error => throwError(error))
      );
  }
}
