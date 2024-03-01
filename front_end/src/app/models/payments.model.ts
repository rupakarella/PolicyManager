import { UserPolicies } from "./userpolicies.model";
import { Users } from "./users.model";


export interface Payments {
    paymentId: number;
    paymentDate: Date;
    paymentStatus: 'Pending' | 'Completed';
    totalAmount: number;
    fine: number;
    paymentMethod: 'Credit Card' | 'Debit Card' | 'Net Banking' | 'Cash';
    userPolicies?: UserPolicies;
    userPolicyId?:number;
    users?:Users;
    userId?:any;
}