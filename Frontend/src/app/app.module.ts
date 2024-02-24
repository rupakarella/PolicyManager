import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PaymentsComponent } from './components/payments/payments.component';

import { UsersComponent } from './components/users/users.component';

import { ClaimsComponent } from './components/claims/claims.component';
import { LoginComponent } from './components/login/login.component';
import { PoliciesComponent } from './components/policies/policies.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UserPoliciesComponent } from './components/user-policies/user-policies.component';



@NgModule({
  declarations: [
    AppComponent,
    PaymentsComponent,
    UsersComponent,
    ClaimsComponent,
    LoginComponent,
    PoliciesComponent,
    HomeComponent,
    RegistrationComponent,
    UserDashboardComponent,
    ProfileComponent,
    UserPoliciesComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
