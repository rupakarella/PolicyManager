import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { Users } from 'src/app/models/users.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JwtService } from 'src/app/service/jwt.service';
import { NavigationService } from 'src/app/service/navigation.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user!: Users;
  isEdit: boolean = false;
  usersForm!: FormGroup;
  submitted = false;
  showPassword = true;
  response: any;
  public loggedIn=true;
  public UserloggedIn=false;
  public AdminloggedIn=false;


  constructor(private userService: UserService, private router: Router,private formBuilder: FormBuilder,private jwtService:JwtService, private navigationService: NavigationService) { 
  this.usersForm = this.formBuilder.group({
    userId: [],
    emailAddress: ['', [Validators.required, Validators.email]],
    contactNumber: ['', [Validators.required, Validators.pattern('^[6789]\\d{9}$')]],
    password: ['', Validators.required],
    firstName: ['', [Validators.required, Validators.pattern('^[a-zA-Z\\s]+$')]],
    lastName: ['', [Validators.required, Validators.pattern('^[a-zA-Z\\s]+$')]],
    dateOfBirth: ['', Validators.required],
    panNumber: ['', [Validators.required, Validators.pattern('^[A-Z]{5}\\d{4}[A-Z]{1}$')]],
    // employerType: ['', [Validators.required, Validators.maxLength(25)]],
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
    this.navigationService.disableBackButton();
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

  isAdminLoggedIn() {
    return localStorage.getItem('token') !== null && localStorage.getItem('userType') === 'Admin';
  }

  isUserLoggedIn() {
    return localStorage.getItem('token') !== null && localStorage.getItem('userType') === 'User';
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

  onUpdate()
   {
     this.user.userId=+localStorage.getItem('userId')!;
     this.user.emailAddress=this.usersForm.value.emailAddress;
     this.user.contactNumber=this.usersForm.value.contactNumber;
     this.user.password=this.usersForm.value.password;
     this.user.firstName=this.usersForm.value.firstName;
     this.user.lastName=this.usersForm.value.lastName;
     this.user.dateOfBirth=this.usersForm.value.dateOfBirth;
     this.user.panNumber=this.usersForm.value.panNumber;
    //  this.user.employerType=this.usersForm.value.employerType;
     this.user.employerName=this.usersForm.value.employerName;
     this.user.salary=this.usersForm.value.salary;
     this.user.userType=this.usersForm.value.userType;
     this.user.address.addressLine=this.usersForm.value.address.addressLine;
     this.user.address.city=this.usersForm.value.address.city;
     this.user.address.cityPincode=this.usersForm.value.address.cityPincode;
     this.user.address.state=this.usersForm.value.address.state;

      
      let response = this.userService.updateUser(this.user);

      response.subscribe
      ( 
        responseData => 
        {
          this.response = responseData; 
          console.log(responseData);
          alert("User Details Updated!");
          this.router.navigate(['/login']);


        },
        error =>
        {
          console.log(error);
          alert("Error while updating details");
          window.location.reload();

        });
  }
}