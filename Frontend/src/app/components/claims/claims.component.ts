import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Claims } from 'src/app/models/claims.model';
import { ClaimService } from 'src/app/service/claim.service';

@Component({
  selector: 'app-claims',
  templateUrl: './claims.component.html',
  styleUrls: ['./claims.component.css']
})
export class ClaimsComponent implements OnInit {
  claims: Claims[] = [];
  editClaimForm!: FormGroup;
  showEditForm = false;
  selectedClaim!: Claims;
  userPolicyId!:number;

  constructor(
    private formBuilder: FormBuilder,
    private claimsService: ClaimService
  ) { }

  ngOnInit(): void {
    this.getAllClaims(),
      this.editClaimForm = this.formBuilder.group({
        userPolicyId: [null, Validators.required],
        claimDate: [null, Validators.required],
        claimAmount: [null, Validators.required],
        claimStatus: ['Pending', Validators.required]
      });
  }

  editClaim(claim: Claims) {
  
    this.selectedClaim = claim;
    this.editClaimForm.patchValue({
      userPolicyId: claim.userPolicy ? claim.userPolicy.userPolicyId : null,
      claimDate: claim.claimDate,
      claimAmount: claim.claimAmount,
      claimStatus: claim.claimStatus
    });
    this.showEditForm = true;
  }

  onSubmit() {
    if (this.editClaimForm.valid) {
      const updatedClaim: Claims = {
        userPolicyId:this.editClaimForm.value.userPolicyId,
        claimId: this.selectedClaim.claimId,
        claimDate: this.editClaimForm.value.claimDate,
        claimAmount: this.editClaimForm.value.claimAmount,
        claimStatus: this.editClaimForm.value.claimStatus,
      };

      this.claimsService.updateClaim(updatedClaim).subscribe(
        () => {
          this.showEditForm = false;
          this.getAllClaims();
        },
        (        error: any) => {
          console.error('Error updating claim', error);
        }
      );
    }
  }

  get f(){
    return this.editClaimForm.controls;
  }
  
  getAllClaims(): void {
    this.claimsService.getAllClaims().subscribe(
      (data) => {
        this.claims = data;
      },
      (error) => {
        console.error('Error fetching claims', error);
      }
    );
  }
}