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

  constructor(private authService:AuthService,private reunionService:ReunionService) {
  }
  ngOnInit() {
    this.reunionService.getReunionbyuserid(this.authService.getLoggedInUser().id_user).subscribe(reunions => {
      this.reunions = reunions;
    });
  }

}
