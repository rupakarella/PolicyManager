import { Component, OnInit } from '@angular/core';
import { UserPolicies } from 'src/app/models/userpolicies.model';
import { UserPolicyService } from 'src/app/service/user-policies.service';

@Component({
  selector: 'app-user-policies',
  templateUrl: './user-policies.component.html',
  styleUrls: ['./user-policies.component.css']
})

export class UserPoliciesComponent implements OnInit {
  userPolicies: UserPolicies | undefined;

  constructor(private userPolicyService: UserPolicyService) { }

  ngOnInit(): void {
    this.getUserPoliciesByUserId();
  }

  getUserPoliciesByUserId() {
    this.userPolicyService.getUserPoliciesbyUserId(localStorage.getItem('userId')).subscribe(
      (data: UserPolicies) => {
        this.userPolicies = data;
      },
      (error) => {
        console.log('Error fetching user policies:', error);
      }
    );
  }
}
