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
    // this.userPolicies.forEach(userPolicy => {
    //   // Convert start date to Date object
    //   const startDate = new Date(userPolicy.startDate);
      
    //   // Add duration in years to start date to calculate end date
    //   const endDate = new Date(startDate.getTime()); // Create a copy of start date
    //   endDate.setFullYear(endDate.getFullYear() + userPolicy.durationInYears); // Add duration
      
    //   // Update userPolicy's end date
    //   userPolicy.endDate = endDate.toISOString().split('T')[0]; // Convert to string in 'yyyy-mm-dd' format
    // });
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