import { UserPolicies } from "./userpolicies.model";


export interface Claims {
    claimId: number;
    claimDate: Date;
    claimAmount: number;
    claimStatus: string;
    userPolicy?: UserPolicies;
    userPolicyId?:number;

}