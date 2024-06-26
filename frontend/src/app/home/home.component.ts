  import {Component, OnInit} from '@angular/core';
  import {AuthService} from "../auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  loggedInUser: any;

  constructor(private authService : AuthService) {
  }
  ngOnInit() {
    this.loggedInUser = this.authService.getLoggedInUser()
  }

}
