import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { AuthRequest } from 'src/app/models/AuthRequest';
import { JwtService } from 'src/app/service/jwt.service';
import { NavigationService } from 'src/app/service/navigation.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  submitted = false;
  showPassword = true;
  // email: any;
  // password: any;
  response: any;
  token: any;

  authRequest: AuthRequest = new AuthRequest();
  formData: any;
  constructor(private formBuilder: FormBuilder, private router: Router, private jwtService: JwtService, private navigationService: NavigationService) { }

  ngOnInit(): void {
    this.navigationService.disableBackButton();
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get f() {
    return this.loginForm.controls;
  }


  readFormData() {
    if (this.jwtService.isLoggedIn()) {
      // User is already logged in, prevent login and show alert
      alert("You are already logged in. Please logout before logging in as a different user.");
      return;
    }
  
    if (this.loginForm.invalid) {
      // If the form is invalid, mark all fields as touched to display validation messages
      this.loginForm.markAllAsTouched();
      return;
    }
  
    const username = this.loginForm.get('email')?.value;
    const password = this.loginForm.get('password')?.value;
  
    this.authRequest.username = username;
    this.authRequest.password = password;
  
    this.jwtService.getGeneratedToken(this.authRequest).subscribe(
      (response: any) => {
        console.log(response);
        this.jwtService.loginUser(response);
        if (response.userType === 'Admin') {
          alert("Admin Login Successful");
          this.router.navigate(['/admin-dashboard']);
        } else if (response.userType === 'User') {
          alert("User Login Successful");
          this.router.navigate(['/user-dashboard']);
        }
      },
      error => {
        console.log(error);
        alert("Invalid Username or Password");
        window.location.reload();
      }
    );
  }
  
  public getAccessToken(authRequest: any) {
    let response = this.jwtService.getGeneratedToken(authRequest);
    response.subscribe((genToken) => { this.token = genToken; console.log(genToken) });
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
}





