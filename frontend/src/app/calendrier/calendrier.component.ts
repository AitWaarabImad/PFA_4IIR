import {Component, OnInit} from '@angular/core';
import {CalendarEvent, CalendarView} from "angular-calendar";
import {Subject} from "rxjs";
import {colors} from "@angular/cli/src/utilities/color";
import {AuthService} from "../auth.service";
import {ReunionService} from "../reunion.service";
import {Reunion} from "../reunion";
import {InviteService} from "../invite.service";

@Component({
  selector: 'app-calendrier',
  templateUrl: './calendrier.component.html',
  styleUrl: './calendrier.component.css'
})
export class CalendrierComponent implements OnInit{

  view: CalendarView = CalendarView.Month;
  selectedMeeting: any; // Variable pour stocker les détails de la réunion sélectionnée

  reunions!: Reunion[]
  viewDate = new Date();

  events: CalendarEvent[] = [];
  ev!: CalendarEvent;

  protected readonly CalendarView = CalendarView;

  constructor(private authService:AuthService,private reunionService:ReunionService,private inviteService : InviteService) {
  }
  setView(view: CalendarView) {
    this.view = view;

  }

  ngOnInit(): void {


    const id_user = this.authService.getLoggedInUser().id_user;

    this.reunionService.getReunionbyuserid(id_user).subscribe(reunions => {
      this.reunions = reunions;
    });
    this.reunionService.getReunionbyrappid(id_user).subscribe(reunnions => {
        this.reunions = this.reunions.concat(reunnions)
      });
    this.inviteService.getIdsofReu(id_user).subscribe(response => {

      this.reunionService.getReubyid(response).subscribe(reunion => {
        this.reunions = this.reunions.concat(reunion)
        this.events = this.reunions.map(reunion => ({
          title: reunion.titre,
          start: new Date(reunion.debutR),
          end: new Date(reunion.finReu),
          id: reunion.id_Re
        }))
      })
    });
  }






  // Méthode pour afficher les détails de la réunion lorsque vous cliquez sur un jour
  showMeetingDetails(date: Date) {
    // Vérifiez si une réunion existe pour la date sélectionnée
    const meeting = this.events.find(event => event.start.getDate() === date.getDate() && event.start.getMonth() === date.getMonth() && event.start.getFullYear() === date.getFullYear());

    // Si une réunion existe, stockez ses détails dans selectedMeeting et affichez la modal
    if (meeting) {
      this.selectedMeeting = meeting;
      // Affichez la modal
    }
  }

  // Méthode pour fermer la modal
  closeModal() {
    this.selectedMeeting = null;
    // Cachez la modal
  }


}
