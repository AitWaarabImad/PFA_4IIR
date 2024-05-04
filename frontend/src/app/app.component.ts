import {Component, OnInit} from '@angular/core';
import {User} from "./user";
import {AuthService} from "./auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'frontend';
  username!:string;
  constructor(private auth:AuthService) {
  }
  ngOnInit(): void {
    console.log(this.auth.getnameById(4).subscribe(
      (username:string) => {
        console.log(username)
      },
      (error: any) => {
        console.error('une erreur',error) ;
      }
    ))
  }



}
