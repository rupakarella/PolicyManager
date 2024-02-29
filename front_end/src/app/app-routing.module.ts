import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PoliciesComponent } from './components/policies/policies.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UserPoliciesComponent } from './components/user-policies/user-policies.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { ManageUsersComponent } from './components/manage-users/manage-users.component';
import { ExplorePoliciesComponent } from './components/explore-policies/explore-policies.component';
import { ClaimsComponent } from './components/claims/claims.component';
import { PaymentsComponent } from './components/payments/payments.component';
import { AuthGuard } from './service/auth.guard';
import { AdminAuthGuard } from './service/admin-auth.guard';


const routes: Routes = [
  {path:'policies',component: PoliciesComponent},
  {path: '', component: HomeComponent,pathMatch:'full' },
  {path: 'login', component: LoginComponent,pathMatch:'full'},
  {path:'registration', component: RegistrationComponent,pathMatch:'full'},
  {path:'user-dashboard', component: UserDashboardComponent,canActivate: [AuthGuard]},
  {path:'profile', component: ProfileComponent,canActivate: [AuthGuard,AdminAuthGuard]},
  {path:'user-policies',component:UserPoliciesComponent,canActivate: [AuthGuard,AdminAuthGuard]},
  {path:'admin-dashboard', component: AdminDashboardComponent,canActivate: [AdminAuthGuard]},
  {path:'manage-users',component:ManageUsersComponent,canActivate: [AdminAuthGuard]},
  {path:"explore-policies",component:ExplorePoliciesComponent,pathMatch:'full'},
  {path:"claims",component:ClaimsComponent,canActivate: [AuthGuard,AdminAuthGuard]},
  {path:"payments",component:PaymentsComponent,canActivate: [AuthGuard,AdminAuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }