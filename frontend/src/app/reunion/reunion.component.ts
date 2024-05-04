import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {ReunionService} from "../reunion.service";
import {Reunion} from "../reunion";

@Component({
  selector: 'app-reunion',
  templateUrl: './reunion.component.html',
  styleUrl: './reunion.component.css'
})
export class ReunionComponent implements OnInit{

  reunions: Reunion[] = [];
  rapporteur!:any;
  organisateur!:any;

  constructor(private authService:AuthService,private reunionService:ReunionService) {
  }
  ngOnInit() {
    this.reunionService.getReunionbyuserid(this.authService.getLoggedInUser().id_user).subscribe(reunions => {
      this.reunions = reunions;

    });
  }
  getnamebyid(id:any){
    return this.authService.getnameById(id).subscribe(
      (username:string) => {
        return username;
      },
      (error: any) => {
        console.error('une erreur',error) ;
      }
    )
  }
}
