import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  loginForm!: FormGroup;
submitted: any;
email: any;
password: any;
  constructor(route:ActivatedRoute) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    });
  }

  onLogin(): void {
    if (this.loginForm.valid) {
      console.log('Form is valid, proceed with login.');
      alert('Login successful!');
    }else{
      alert('PLease Fill out the form correctly');
    }

    }
  



  

 

  

}
