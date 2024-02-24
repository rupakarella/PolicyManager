import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { Users } from 'src/app/models/users.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JwtService } from 'src/app/service/jwt.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: Users | undefined;
  isEdit: boolean = false;
  usersForm!: FormGroup;
  submitted = false;
  showPassword = true;
  public loggedIn=false;
  public UserloggedIn=false;
  public AdminloggedIn=false;
  

  constructor(private userService: UserService, private router: Router,private formBuilder: FormBuilder,private jwtService:JwtService) { 
  this.usersForm = this.formBuilder.group({
    userId: [],
    emailAddress: ['', [Validators.required, Validators.email]],
    contactNumber: ['', [Validators.required, Validators.pattern('^[6789]\\d{9}$')]],
    password: ['', Validators.required],
    firstName: ['', [Validators.required, Validators.pattern('^[a-zA-Z\\s]+$')]],
    lastName: ['', [Validators.required, Validators.pattern('^[a-zA-Z\\s]+$')]],
    dateOfBirth: ['', Validators.required],
    panNumber: ['', [Validators.required, Validators.pattern('^[A-Z]{5}\\d{4}[A-Z]{1}$')]],
    employerType: ['', [Validators.required, Validators.maxLength(25)]],
    employerName: [],
    salary: ['', Validators.min(0)],
    userType: ['', [Validators.required, Validators.pattern('^(Admin|User)$')]],
    address: this.formBuilder.group({
      addressLine: ['', Validators.required],
      city: ['', Validators.required],
      cityPincode: ['', Validators.required],
      state: ['', Validators.required]
    }),
    userPolicies: this.formBuilder.array([])
  });
}
  ngOnInit(): void {
    this.getUserById();
    {
      this.loggedIn = this.jwtService.isUserLoggedIn() || this.jwtService.isAdminLoggedIn();
      this.UserloggedIn =this.jwtService.isUserLoggedIn();
      this.AdminloggedIn = this.jwtService.isAdminLoggedIn();
      this.router.navigate(['/profile']);
    }
  }
  logoutUser()
  {
    this.jwtService.logout();
    this.loggedIn=false;
    this.UserloggedIn=false;
    this.AdminloggedIn=false;
    alert("Logged Out");
    this.router.navigate(['/']);
  }
  


  getUserById() {
    this.userService.getUserById(localStorage.getItem('userId')).subscribe(
      (data: Users) => {
        this.user = data;
      },
      (error) => {
        console.log('Error fetching user details:', error);
      }
    );
  }

  onEditClicked() {
    this.isEdit = true;
  }
  get f() {
    return this.usersForm.controls;
  }
  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  onUpdate(user: Users) {
    // Call your service method to update the user
    // this.userService.updateUser(user).subscribe(
    //   (response) => {
    //     console.log('User updated successfully:', response);
    //     this.isEdit = false; // Switch back to profile view after successful update
    //     // Optionally, you can refresh the user data after update
    //     this.getUserById();
    //   },
    //   (error) => {
    //     console.log('Error updating user:', error);
    //     // Handle error scenario
    //   }
    // );
  }
}