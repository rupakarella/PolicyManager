import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Payments } from '../models/payments.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentsService {
  baseUrl = 'http://localhost:8080/api/v1/payments/';

  constructor(private http: HttpClient) { }

  getAllPayments(): Observable<Payments[]> {
    let tokenString = "Bearer " + localStorage.getItem("token");
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this.http.get<Payments[]>(this.baseUrl + "get-all", { headers });
  }
  makePayment(payments: Payments): Observable<Payments[]> {
    let tokenString = "Bearer " + localStorage.getItem("token");
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this.http.post<Payments[]>(this.baseUrl + "register", payments, { headers });

  }
}