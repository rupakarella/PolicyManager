import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Users } from '../models/users.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl:string = 'http://localhost:8080/api/v1/users/';
  
  constructor(private http:HttpClient) { }

  registerUser(user: Users): Observable<any>
    {
      return this.http.post<any>(this.baseUrl+"register",user,{responseType:'json'})
    }
    getUserById(userId:any)
  {
    let tokenString = "Bearer "+localStorage.getItem("token");
    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);
    return this.http.get<Users>(this.baseUrl+"get-userId/"+userId,{headers,responseType:'json'});

  }
  updateUser(user:any)
  {
    let tokenString = "Bearer "+localStorage.getItem("token");
    const headers =  new HttpHeaders().set("Authorization",tokenString);
    return this.http.put(this.baseUrl+"update",user,{headers,responseType:'json'});
  }

 
}