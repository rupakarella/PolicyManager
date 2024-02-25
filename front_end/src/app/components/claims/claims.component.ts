import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-claims',
  templateUrl: './claims.component.html',
  styleUrls: ['./claims.component.css']
})
export class ClaimsComponent implements OnInit {
  claimsForm!: FormGroup;
  submitted= false;

  constructor(private formBuilder: FormBuilder) { }


  ngOnInit(): void {  
    this.claimsForm = this.formBuilder.group({
      claimId: [],
      claimDate: ['', [Validators.required]],
      claimAmount: ['', [Validators.required]],
      claimStatus: ['Pending', [Validators.required]],
      userPolicyId: ['', [Validators.required]]
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.claimsForm.invalid) {
      console.log(this.claimsForm.value);
      return;
    }
    else {
      alert('Claim Registered successfully ');
    }

  }

  }
 


