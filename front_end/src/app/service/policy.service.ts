import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Policy } from '../models/policy.model';

@Injectable({
  providedIn: 'root'
})
export class PolicyService {
  private apiUrl = 'http://localhost:8080/api/v1/policies';

  constructor(private http:HttpClient) { }

  registerPolicy(policy: Policy): Observable<Policy> {
    return this.http.post<Policy>(this.apiUrl + "/register", policy);
  }
  
  updatePolicy(policy: Policy): Observable<Policy> {
    return this.http.put<Policy>(this.apiUrl+"/update",policy);
  }

  getPoliciesByPolicyType(policyType: string): Observable<Policy[]> {
    return this.http.get<Policy[]>(this.apiUrl + '/get-policyType/' + policyType);
  }

  getPoliciesByCompany(company: string): Observable<Policy[]> {
    return this.http.get<Policy[]>(this.apiUrl + '/get-company/' + company);
  }

  getByAmountGreaterThan(termAmount: number): Observable<Policy[]> {
    return this.http.get<Policy[]>(this.apiUrl + '/get-term-amount-greater/' + termAmount);
  }

  getByAmountLessThan(termAmount: number): Observable<Policy[]> {
    return this.http.get<Policy[]>(this.apiUrl + '/get-term-amount-lower/' + termAmount);
  }

  getAllPolicies(): Observable<Policy[]> {
    return this.http.get<Policy[]>(this.apiUrl + '/get-all');
  }


  deletePolicy(policyId: number): Observable<any> {
    return this.http.delete(this.apiUrl+ '/delete/'+policyId);
  }
}
