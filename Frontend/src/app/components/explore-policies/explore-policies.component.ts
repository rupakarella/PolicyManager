import { PlatformLocation } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Policy } from 'src/app/models/policy.model';
import { UserPolicies } from 'src/app/models/userpolicies.model';
import { Users } from 'src/app/models/users.model';
import { JwtService } from 'src/app/service/jwt.service';
import { PolicyService } from 'src/app/service/policy.service';
import { UserPoliciesService } from 'src/app/service/user-policies.service';

@Component({
  selector: 'app-explore-policies',
  templateUrl: './explore-policies.component.html',
  styleUrls: ['./explore-policies.component.css']
})
export class ExplorePoliciesComponent implements OnInit {

  policies: Policy[] = [];
  userPolicies: UserPolicies = {
    userPolicyId: 0,
    userId: 0,
    policyId: 0,
    startDate: new Date(),
    durationInYears: 0,
    
  };
  policy!: Policy;
  showForm:boolean=false;
  isEdit: boolean = false;
  buyForm: FormGroup;
  showBuyForm: boolean = false;
  policyId: number = 0;
  response: any;
  newPolicyForm!: FormGroup;
  policiesForm!: FormGroup;
  selectedPolicyType: string = '';
  selectedFilterMethod: string = '';
  company: string = '';
  greaterThanAmount: number = 0;
  lessThanAmount: number=0;
  registerForm!:FormGroup;
  public loggedIn=false;
  public UserloggedIn=false;
  public AdminloggedIn=false;
  constructor(
    private policyService: PolicyService,
    private formBuilder: FormBuilder,
    private userPoliciesService: UserPoliciesService,
    private router: Router,
    private jwtService: JwtService,
    private platformlocation: PlatformLocation
  ) {
    this.buyForm = this.formBuilder.group({
      startDate: ['', Validators.required],
      durationInYears: ['', Validators.required],   
    });
    this.policiesForm = this.formBuilder.group({
      policyName: ['', Validators.required],
      policyDescription: ['', Validators.required],
      company: ['', Validators.required],
      policyType: ['', [Validators.required]],
      initialDeposit: [0, [Validators.required, Validators.min(1)]],
      termPeriod: ['', [Validators.required, Validators.pattern('^(Monthly|Quarterly|Half-Yearly|Annually)$')]],
      termAmount: [0, [Validators.required, Validators.min(1)]],
      interest: [0, [Validators.required, Validators.min(0)]],
      eligibleUserTypes:[[],[Validators.required]]
    });
    this.registerForm=this.formBuilder.group({
      policyName: ['', Validators.required],
      policyDescription: ['', Validators.required],
      company: ['', Validators.required],
      policyType: ['', [Validators.required]],
      initialDeposit: [0, [Validators.required, Validators.min(1)]],
      termPeriod: ['', [Validators.required, Validators.pattern('^(Monthly|Quarterly|Half-Yearly|Annually)$')]],
      termAmount: [0, [Validators.required, Validators.min(1)]],
      interest: [0, [Validators.required, Validators.min(0)]],
      eligibleUserTypes:[[],[Validators.required]]
    })
  }


  toggleFormVisibility() {
    this.showForm = !this.showForm;
    this.isEdit = false; 
    console.log('Form visibility toggled:', this.showForm);
}

  onAdding(): void {
    if (this.registerForm.invalid) {
      return;
    }
    const newPolicy = this.registerForm.value;
    this.policyService.registerPolicy(newPolicy)
    .subscribe(
      (response) => {
        console.log('Policy added successfully', response);
        alert('Policy added successfully');
        // Reset the form after successful addition
        this.registerForm.reset();
        // Optionally, toggle the form visibility off
        this.toggleFormVisibility();
      },
      (error) => {
        console.error('Error adding policy:', error);
        alert('Failed to add policy');
      }
    );
}

  ngOnInit(): void {
    this.loadPolicies();
    history.pushState(null,'',location.href);
    this.platformlocation.onPopState(() => {
      history.pushState(null,'',location.href);
    });
  }
  logoutUser()
  {
    this.jwtService.logout();
    this.loggedIn=false;
    this.UserloggedIn=false;
    this.AdminloggedIn=false;
    alert("Logged Out");
    this.router.navigate(['/']);
  }

  isAdminLoggedIn() {
    return localStorage.getItem('token') !== null && localStorage.getItem('userType') === 'Admin';
  }

  isUserLoggedIn() {
    return localStorage.getItem('token') !== null && localStorage.getItem('userType') === 'User';
  }



  loadPolicies(): void {
    this.policyService.getAllPolicies().subscribe(
      (data) => {
        this.policies = data;
      },
      (error) => {
        console.error('There was an error fetching the policies', error);
      }
    );
  }

  get f() {
    return this.buyForm.controls;
  }
  get f1() {
    return this.policiesForm.controls;
  }
  get f2()
  {
    return this.registerForm.controls;
  }


  buyPolicy(policyId: number) {
    this.showBuyForm = true;
    this.policyId = policyId;
    this.userPolicies.userId = +localStorage.getItem('userId')!;
  }

  onBuyPolicy() {
    this.userPolicies.policyId = this.policyId;
    this.userPolicies.userId = +localStorage.getItem('userId')!;
    this.userPolicies.startDate = this.buyForm.value.startDate;
    this.userPolicies.durationInYears = this.buyForm.value.durationInYears;

    this.userPoliciesService.registerUserPolicies(this.userPolicies).subscribe(
      responseData => {
        console.log(responseData);
        alert("Policy added to your account");
        this.router.navigate(['/user-policies']);
      },
      error => {
        console.log(error);
        console.log("failed to register");
        alert("User policy buying failed");
        window.location.reload();
      }
    );
  }
  deletePolicy(policyId: number) {
    this.policyService.deletePolicy(policyId).subscribe(
      (response) => {
        console.log(response); 
        this.loadPolicies();
      },
      (error) => {
        console.log(error);
      }
    );
  }
 
  updatePolicy(policies: Policy) {
    this.isEdit = true;
    this.policy = policies;
    this.policiesForm.patchValue({
      policyId: policies.policyId,
      policyName: policies.policyName,
      policyDescription: policies.policyDescription,
      company: policies.company,
      policyType: policies.policyType,
      initialDeposit: policies.initialDeposit,
      termPeriod: policies.termPeriod,
      termAmount: policies.termAmount,
      interest: policies.interest
    })
  }
 
  
  onUpdate() {
    this.policy.policyName = this.policiesForm.value.policyName;
    this.policy.policyDescription = this.policiesForm.value.policyDescription;
    this.policy.company = this.policiesForm.value.company;
    this.policy.initialDeposit = this.policiesForm.value.initialDeposit;
    this.policy.policyType=this.policiesForm.value.policyType;
    this.policy.termPeriod = this.policiesForm.value.termPeriod;
    this.policy.termAmount = this.policiesForm.value.termAmount;
    this.policy.interest = this.policiesForm.value.interest;

    let response = this.policyService.updatePolicy(this.policy);
    response.subscribe(
      responseData => {
        this.response = responseData;
        console.log(responseData);
        alert("Policy Details Updated!");
        window.location.reload();
      },
      error => {
        console.log(error);
        alert("Error while updating details");
        window.location.reload();
      }
    );
  }
  onFilterMethodChange(event: Event): void {
    const value = (event.target as HTMLSelectElement).value;
    if (value === 'getPolicyByPolicyType' || value === 'getPoliciesByCompany' || value=='getByAmountGreaterThan'|| value=='getByAmountLessThan') {
      this.selectedFilterMethod = value;
    }
  }
  onPolicyTypeChange(event: Event): void {
    this.selectedPolicyType = (event.target as HTMLSelectElement).value;
    this.getPoliciesByPolicyType(this.selectedPolicyType);
  }

  getPoliciesByPolicyType(policyType: string): void {

    this.policyService.getPoliciesByPolicyType(policyType).subscribe(
      (response) => {
        if (Array.isArray(response)) {
          this.policies = response;
          console.log(this.policies);
        } else if (typeof response === 'object') {
          this.policies = Object.values(response);
        } else {
          console.error('Unexpected response format');
        }
      });
  }
  onGetPoliciesByCompany(): void {
    this.getPoliciesByCompany(this.company);
  }


  getPoliciesByCompany(company: string): void {
    this.policyService.getPoliciesByCompany(company).subscribe(
      (response) => {
        if (Array.isArray(response)) {
          this.policies = response;
          console.log(this.policies);
        } else if (typeof response === 'object') {
          this.policies = Object.values(response);
        } else {
          console.error('Unexpected response format');
        }
      });
  }
  onGetPoliciesByAmountGreaterThan(): void {
    this.getByAmountGreaterThan(this.greaterThanAmount);
  }

  getByAmountGreaterThan(greaterThanAmount: number): void {
    if (this.greaterThanAmount > 0) {
      this.policyService.getByAmountGreaterThan(greaterThanAmount).subscribe(
        (response) => {
          this.policies = response; 
        },
        (error) => {
          console.error('Error fetching policies by amount greater than:', error);
        }
      );
    } else {
      console.error('Please enter a valid amount greater than zero');
    }
  }
  onGetPoliciesByAmountLessThan(): void {
    this.getByAmountLessThan(this.lessThanAmount);
  }

  getByAmountLessThan(lessThanAmount: number): void {
    if (this.lessThanAmount > 0) {
      this.policyService.getByAmountLessThan(lessThanAmount).subscribe(
        (response) => {
          this.policies = response; 
        },
        (error) => {
          console.error('Error fetching policies by amount less than:', error);
        }
      );
    } else {
      console.error('Please enter a valid amount greater than zero');
    }
  }


}