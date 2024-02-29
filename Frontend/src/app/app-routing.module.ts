import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UserPoliciesComponent } from './components/user-policies/user-policies.component';
import { ExplorePoliciesComponent } from './components/explore-policies/explore-policies.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { ManageUsersComponent } from './components/manage-users/manage-users.component';
import { ClaimsComponent } from './components/claims/claims.component';
import { PaymentsComponent } from './components/payments/payments.component';


const routes: Routes = [
  
  { path: '', component: HomeComponent },
  {path:'registration', component: RegistrationComponent},
  {path: 'login',component: LoginComponent},
  { path:'explore-policies',component:ExplorePoliciesComponent},
  {path:'profile',component: ProfileComponent},
  {path:'user-dashboard',component: UserDashboardComponent},
  {path:'user-policies',component: UserPoliciesComponent},
  {path:'admin-dashboard',component: AdminDashboardComponent},
  {path:'manage-users',component: ManageUsersComponent},
  {path:'claims',component: ClaimsComponent},
  {path:'payments',component: PaymentsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }