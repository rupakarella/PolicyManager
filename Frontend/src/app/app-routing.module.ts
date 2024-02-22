import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PoliciesComponent } from './components/policies/policies.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/registration/registration.component';

const routes: Routes = [
  { path:'policies',component: PoliciesComponent},
  { path: '', component: HomeComponent },
  {path:'registration', component: RegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }