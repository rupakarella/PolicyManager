import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PolicyService } from 'src/app/service/policy.service';
import { Policy } from 'src/app/models/policy.model';
import { NavigationService } from 'src/app/service/navigation.service';

@Component({
  selector: 'app-policies',
  templateUrl: './policies.component.html',
  styleUrls: ['./policies.component.css']
})
export class PoliciesComponent {
deletePolicy(arg0: number) {
throw new Error('Method not implemented.');
}


editPolicy(_t20: Policy) {
throw new Error('Method not implemented.');
}
  policies: Policy[] = [];
  policyForm!: FormGroup;

  constructor(private fb: FormBuilder, private policyService: PolicyService,private navigationService: NavigationService ) { }
  
  ngOnInit(): void {
    this.navigationService.disableBackButton();
    this.initializeForm();
    this.loadPolicies();
  }
  initializeForm(): void {
    this.policyForm = this.fb.group({
      policyName: ['', Validators.required],
      policyDescription: ['', Validators.required],
      company: ['', Validators.required],
      policyType: ['', [Validators.required]],
      initialDeposit: [0, [Validators.required, Validators.min(1)]],
      termPeriod: ['', [Validators.required, Validators.pattern('^(Monthly|Quarterly|Half-Yearly|Annually)$')]],
      termAmount: [0, [Validators.required, Validators.min(1)]],
      interest: [0, [Validators.required, Validators.min(0)]]
    });
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

  onSubmit(): void {
    if (this.policyForm.valid) {
      const newPolicy: Policy = this.policyForm.value as Policy;
      this.policyService.registerPolicy(newPolicy).subscribe(
        (policy) => {
          this.policies.push(policy);
          this.policyForm.reset();
        },
        (error) => {
          console.error('There was an error registering the policy', error);
        }
      );
    }
  }
}