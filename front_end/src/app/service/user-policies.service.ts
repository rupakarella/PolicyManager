import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserPolicies } from '../models/userpolicies.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserPolicyService {

    baseUrl:string = 'http://localhost:8080/api/v1/userPolicies/';
    
    constructor(private http:HttpClient) { }
  
    registerUser(userpolicies: UserPolicies): Observable<any>
      {
        return this.http.post<any>(this.baseUrl+"register",userpolicies,{responseType:'json'})
      }

      getUserPoliciesbyUserId(userId:any)
    {
      let tokenString = "Bearer "+localStorage.getItem("token");
      const headers =  new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:4200'
      }).set("Authorization",tokenString);
      return this.http.get<UserPolicies>(this.baseUrl+"get-by-userId/"+userId,{headers,responseType:'json'});
  
    }
  
   
  }