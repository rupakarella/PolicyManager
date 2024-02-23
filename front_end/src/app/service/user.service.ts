import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Users } from '../models/users.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl:string = 'http://localhost:8080/api/v1/users';
  
  constructor(private http:HttpClient) { }

  registerUser(user: Users): Observable<any>
    {
      return this.http.post<any>(this.baseUrl+"/register",user,{responseType:'json'})
    }

 
}
