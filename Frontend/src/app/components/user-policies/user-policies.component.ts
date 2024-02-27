import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  editUPForm!: FormGroup;
  showEditForm = false;
  selectedUP!: UserPolicies;
  selectedFilterMethod: string = '';
  userPolicyId:number=0;
  userId:number=0;

  constructor(private userPoliciesService: UserPoliciesService, private formbuilder:FormBuilder) {
    
   }

  ngOnInit(): void {
    
    if(this.isUserLoggedIn())
    {
      this.getUserPoliciesByUserId();
    }
    else if(this.isAdminLoggedIn()){
      this.getAllUserPolicies(); 
      this.editUPForm = this.formbuilder.group({
        startDate: ['', Validators.required],
        durationInYears: ['', Validators.required],
        policyId: ['', Validators.required]
      });
    }
    
  }
  editUserPolicy(userPolicy: UserPolicies) {
  
    this.selectedUP = userPolicy;
    this.editUPForm.patchValue({
      startDate: userPolicy.startDate,
      durationInYears: userPolicy.durationInYears,
      policyId: userPolicy.policy?.policyId
    });
    this.showEditForm = true;
  }

  onSubmit() {
    if (this.editUPForm.valid) {
      const updateUserPolicy: UserPolicies = {
        
        userPolicyId: this.selectedUP.userPolicyId,
        startDate: this.editUPForm.value.startDate,
        durationInYears: this.editUPForm.value.durationInYears,
        policyId: this.editUPForm.value.policyId,
        userId: this.selectedUP.userId
      };

      this.userPoliciesService.updateUserPolicies(updateUserPolicy).subscribe(
        () => {
          this.showEditForm = false;
          this.getAllUserPolicies();
        },
        (error: any) => {
          console.error('Error updating user policy', error);
        }
      );
    }
  }

  get f(){
    return this.editUPForm.controls;
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
  getAllUserPolicies(){
    this.userPoliciesService.getAllUserPolicies().subscribe(
      (response) => {
        this.userPolicies=response;
      },
      (error) => {
        console.log('Error fetching user policies:', error);
        this.userPolicies = []; 
      }
    );
  }
  isAdminLoggedIn() {
    return localStorage.getItem('token') !== null && localStorage.getItem('userType') === 'Admin';
  }

  isUserLoggedIn() {
    return localStorage.getItem('token') !== null && localStorage.getItem('userType') === 'User';
  }
  deleteUserPolicy(userPolicyId:number)
  {
    this.userPoliciesService.deleteUserPolicy(this.userPolicies[0].userPolicyId).subscribe(
      (response) => {
        this.userPolicies=response;
      },
      (error) => {
        console.log('Error fetching user policies:', error);
        this.userPolicies = []; 
      }
    );
  }
  onFilterMethodChange(event: Event): void {
    const value = (event.target as HTMLSelectElement).value;
    if (value === 'getUserPolicyByUserPolicyId' || value === 'getUserPolicyByUserId' ) {
      this.selectedFilterMethod = value;
    }
  }
  onGetUserPolicyByUserPolicyId(): void {
    this.getUserPolicyByUserPolicyId(this.userPolicyId);
  }

  getUserPolicyByUserPolicyId(userPolicyId: number): void {
    if (userPolicyId > 0) {
      this.userPoliciesService.getUserPolicyByUserPolicyId(userPolicyId).subscribe(
        (response: UserPolicies) => { // Expecting a single UserPolicies object
          this.userPolicies = [response]; // Wrap the single object in an array
        },
        (error) => {
          console.error('Error fetching registered policies with the given Id:', error);
        }
      );
    } else {
      console.error('Please enter a valid Id');
    }
  }
  
  onGetUserPolicyByUserId(): void {
    this.getUserPolicyByUserId(this.userId);
  }

  getUserPolicyByUserId(userId: number): void {
    if (this.userId > 0) {
      this.userPoliciesService.getUserPoliciesbyUserId(userId).subscribe(
        (response) => {
          this.userPolicies = response; // Update policies array with the fetched policies
        },
        (error) => {
          console.error('Error fetching registered policies with the given Id:', error);
        }
      );
    } else {
      console.error('Please enter a valid Id');
    }
  
  
}
}