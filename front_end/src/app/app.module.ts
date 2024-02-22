import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { PoliciesComponent } from './components/policies/policies.component';
import { PolicyDetailsComponent } from './components/policy-details/policy-details.component';
import { UsersComponent } from './components/users/users.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { ClaimsComponent } from './components/claims/claims.component';
import { ClaimDetailsComponent } from './components/claim-details/claim-details.component';
import { PaymentsComponent } from './components/payments/payments.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './components/home/home.component';
@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    PoliciesComponent,
    PolicyDetailsComponent,
    UsersComponent,
    UserDetailsComponent,
    ClaimsComponent,
    ClaimDetailsComponent,
    PaymentsComponent,
    NavbarComponent,
    HomeComponent,
 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
