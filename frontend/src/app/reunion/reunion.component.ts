import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {ReunionService} from "../reunion.service";
import {Reunion} from "../reunion";
import {User} from "../user";
import {map, Observable} from "rxjs";
import {InviteService} from "../invite.service";
import {Router} from "@angular/router";

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
  ids: any[] = []

  constructor(private authService:AuthService,private reunionService:ReunionService,private inviteService:InviteService, private router :Router) {
  }
  ngOnInit() {

    const userId = this.authService.getLoggedInUser().id_user;

    this.reunionService.getReunionbyuserid(userId).subscribe({
      next: reunions => this.reunions = reunions,
      error: err => console.error('Failed to fetch reunions by user ID', err)
    });


    this.reunionService.getReunionbyrappid(userId).subscribe({
      next: reunions => this.reunions = this.reunions.concat(reunions),
      error: err => console.error('Failed to fetch reunions by RAP ID', err)
    });


    this.inviteService.getIdsofReu(userId).subscribe({
      next: ids => {
        this.reunionService.getReubyid(ids).subscribe({
          next: reunions => this.reunions = this.reunions.concat(reunions),
          error: err => console.error('Failed to fetch reunions by IDs', err)
        });
      },
      error: err => console.error('Failed to fetch IDs of reunions', err)
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

  editRapport(id_rapport: number) {
    this.router.navigate(['/creerrapport'], { queryParams: { id: id_rapport } });  }

  modifierReunion(reunion: Reunion) {

  }

}
