import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Users } from 'src/app/models/users.model';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {
  selectedFilter: string = '';
  userType: string = '';
  contactNumber: string = '';
  emailAddress: string = '';
  filteredUsers: any[] = [];
  users: Users[] = [];
  response: any;
  userTypeFilter: string =''
  email: string='';
  userId: number=0;
  usersForm!: FormGroup;
  isEdit: boolean = false;
  showPassword = true;
  user!: Users;
  submitted = false;



  constructor(private userService:UserService,private router:Router,private formBuilder: FormBuilder){
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

  ngOnInit(): void 
{
  this.getAllUsers();
}

getAllUsers() {
  this.userService.getAllUsers().subscribe(
    (response) => {
      if (Array.isArray(response)) {
        this.users = response;
        console.log(this.users);
      } else if (typeof response === 'object') {
        this.users = Object.values(response);
      } else {
        console.error('Unexpected response format');
      }
    },
    (error) => {
      console.log(error);
    }
  );
}

selectFilter(event: Event) {
  const value = (event.target as HTMLSelectElement).value;
  if (value) {
    this.selectedFilter = value;
    if (value === 'userType') {
      this.userTypeFilter = ''; 
    }else if (value === 'userById') {
      this.userId = 0; 
    }
  }
}




filterByUserType() {
  if (this.userType) {
    this.userService.getUserByUserType(this.userType).subscribe(
      (response: Users[]) => {
        this.users = response;
        console.log(this.users);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}

filterByContactNumber() {
  if (this.contactNumber) {
    this.userService.getUserByContactNumber(this.contactNumber).subscribe(
      (response: Users | Users[]) => {
        if (Array.isArray(response)) {
          this.users = response; // Update the users array with the filtered result
        } else {
          this.users = [response]; // Convert the single object to an array
        }
        console.log(this.users);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
  filterByEmail() {
    if (this.emailAddress) {
      this.userService.getUserByEmail(this.emailAddress).subscribe(
        (response: Users) => {
          this.users = [response]; // Update the users array with the filtered result
          console.log(this.users);
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }


filterByUserId() {
  if (this.userId) {
    this.userService.getUserById(this.userId).subscribe(
      (response: Users) => {
        this.users = [response]; // Update the users array with the filtered result
        console.log(this.users);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
confirmDelete(userId: number) {
  if (confirm("Are you sure you want to delete this user?")) {
    this.deleteUser(userId);
  }
}

deleteUser(userId: number) {
  this.userService.deleteUserById(userId).subscribe(
    (response) => {
      console.log(response); // Log success message
      // After deletion, fetch the updated list of users
      this.getAllUsers();
    },
    (error) => {
      console.log(error); // Log error message
    }
  );
}

onEditClicked(user: Users) {
  this.isEdit = true;
  this.user = user; // Assign the user whose details are being edited to this.user
  // Populate the form with the details of the user
  this.usersForm.patchValue({
    userId: user.userId,
    emailAddress: user.emailAddress,
    contactNumber: user.contactNumber,
   
    firstName: user.firstName,
    lastName: user.lastName,
    dateOfBirth: user.dateOfBirth,
    panNumber: user.panNumber,
    employerType: user.employerType,
    employerName: user.employerName,
    salary: user.salary,
    userType: user.userType,
    address: {
      addressLine: user.address.addressLine,
      city: user.address.city,
      cityPincode: user.address.cityPincode,
      state: user.address.state
    }
  });
}

get f() {
  return this.usersForm.controls;
}
togglePasswordVisibility() {
  this.showPassword = !this.showPassword;
}

onUpdate()
 {
   this.user.emailAddress=this.usersForm.value.emailAddress;
   this.user.contactNumber=this.usersForm.value.contactNumber;
   this.user.password=this.usersForm.value.password;
   this.user.firstName=this.usersForm.value.firstName;
   this.user.lastName=this.usersForm.value.lastName;
   this.user.dateOfBirth=this.usersForm.value.dateOfBirth;
   this.user.panNumber=this.usersForm.value.panNumber;
   this.user.employerType=this.usersForm.value.employerType;
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
        window.location.reload();


      },
      error =>
      {
        console.log(error);
        alert("Error while updating details");
        window.location.reload();

      });
}
}