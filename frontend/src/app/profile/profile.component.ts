import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{
  loggedInUser: any;

  constructor(private authService : AuthService) {
  }
  ngOnInit() {
    this.loggedInUser = this.authService.getLoggedInUser()
  }


}
