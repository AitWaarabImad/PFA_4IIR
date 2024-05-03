import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{

  constructor(private authService: AuthService,private router:Router) {}

  userRole! : string;
  isLoggedin : boolean =false;
  name! : string;
  ngOnInit() {
    this.authService.loggedInUser$.subscribe(user => {
      this.isLoggedin = !!user; // If user exists, set to true, otherwise false
      this.userRole = this.authService.getRoleuser();
      this.name = user.username
    });

  }





  logout() {
    this.authService.logout();
    this.router.navigateByUrl("/login")
  }

}
