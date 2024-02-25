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

const routes: Routes = [
  { path:'policies',component: PoliciesComponent},
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent},
  {path:'registration', component: RegistrationComponent},
  {path:'user-dashboard', component: UserDashboardComponent},
  {path:'profile', component: ProfileComponent},
  {path:'user-policies',component:UserPoliciesComponent},
  {path:'admin-dashboard', component: AdminDashboardComponent},
  {path:'manage-users',component:ManageUsersComponent},
  {path:"explore-policies",component:ExplorePoliciesComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }