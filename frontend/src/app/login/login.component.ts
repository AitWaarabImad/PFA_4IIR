import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import {Router} from "@angular/router";
import {toUnredirectedSourceFile} from "@angular/compiler-cli/src/ngtsc/util/src/typescript";
import {Observable} from "rxjs";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loginFormGroup!: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router : Router) {}

  ngOnInit() {
    this.loginFormGroup = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    if (this.loginFormGroup.valid) {
      const formData =this.loginFormGroup.value
      this.authService.login(formData).subscribe(
        (response) => {
          this.authService.setLoggedInUser(response); // Store user data on successful login
          this.router.navigateByUrl("/home");
          console.log("Login successful", response);
        },
        (error) => {console.log("Username or password uncorrect",error)}
      )
      }
    }

}

