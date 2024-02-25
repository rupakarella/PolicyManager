import { Component, OnInit } from '@angular/core';
import { UserPolicies } from 'src/app/models/userpolicies.model';
import { UserPoliciesService } from 'src/app/service/user-policies.service';

@Component({
  selector: 'app-user-policies',
  templateUrl: './user-policies.component.html',
  styleUrls: ['./user-policies.component.css']
})

export class UserPoliciesComponent implements OnInit {
  userPolicies: UserPolicies[]=[];
  response:any;

  constructor(private userPoliciesService: UserPoliciesService) { }

  ngOnInit(): void {
    this.getUserPoliciesByUserId();
  }
  

  getUserPoliciesByUserId() {
    this.userPoliciesService.getUserPoliciesbyUserId(localStorage.getItem('userId')).subscribe(
      (response) => {
        this.userPolicies=response;
      },
      (error) => {
        console.log('Error fetching user policies:', error);
        this.userPolicies = []; 
      }
    );
  }
  
}