import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {ReunionService} from "../reunion.service";
import {Reunion} from "../reunion";
import {User} from "../user";
import {map, Observable} from "rxjs";

@Component({
  selector: 'app-reunion',
  templateUrl: './reunion.component.html',
  styleUrl: './reunion.component.css'
})
export class ReunionComponent implements OnInit{

  reunions: Reunion[] = [];
  rapporteur!:any;
  organisateur!:any;
  user!: any;

  constructor(private authService:AuthService,private reunionService:ReunionService) {
  }
  ngOnInit() {
    this.reunionService.getReunionbyuserid(this.authService.getLoggedInUser().id_user).subscribe(reunions => {
      this.reunions = reunions;
    });
  }

  supprimerReunion(reunion: Reunion) {
    this.reunionService.DeleteReunion(reunion.id_Re)
      .subscribe(() => {
        // Supprimer la réunion de la liste locale
        this.reunions = this.reunions.filter(r => r !== reunion);
      }
      )
  }

  modifierReunion(reunion: Reunion) {

  }
}
