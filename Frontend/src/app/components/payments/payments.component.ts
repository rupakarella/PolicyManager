import { Component, OnInit } from '@angular/core';
import { Payments } from 'src/app/models/payments.model';
import { PaymentsService } from 'src/app/service/payments.service';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit {
  payments:Payments[]=[];
  constructor(private paymentsService: PaymentsService) { }

  ngOnInit(): void {
    this.getAllPayments();
  }

  getAllPayments() {
    this.paymentsService.getAllPayments().subscribe(
      (data) => {
        this.payments = data;
        console.log(this.payments);
      },
      (error) => {
        console.error('Error fetching payments:', error);
      }
    );
  }
}