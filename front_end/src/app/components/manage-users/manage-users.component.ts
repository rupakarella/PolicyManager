import { Component, OnInit } from '@angular/core';
import { Users } from 'src/app/models/users.model';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {
  users: Users[] = [];
  response: any;



  constructor(private userService:UserService ){}

  ngOnInit(): void 
{
  this.getAllUsers();
}


getAllUsers() {
  this.userService.getAllUsers().subscribe(
    (response) => {
      // Check if response is an array or object
      if (Array.isArray(response)) {

        // If response is already an array, assign it to users directly
        this.users = response;
        console.log(this.users);
      } else if (typeof response === 'object') {
        // If response is an object, extract the array of users
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
}


