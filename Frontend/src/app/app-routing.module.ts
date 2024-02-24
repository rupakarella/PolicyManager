import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PoliciesComponent } from './components/policies/policies.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UserPoliciesComponent } from './components/user-policies/user-policies.component';


const routes: Routes = [
  
  { path: '', component: HomeComponent },
  {path:'registration', component: RegistrationComponent},
  {path: 'login',component: LoginComponent},
  { path:'policies',component: PoliciesComponent},
  {path:'profile',component: ProfileComponent},
  {path:'user-dashboard',component: UserDashboardComponent},
  {path:'user-policies',component: UserPoliciesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }