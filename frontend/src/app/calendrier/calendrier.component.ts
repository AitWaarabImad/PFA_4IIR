import {Component, OnInit} from '@angular/core';
import {CalendarEvent, CalendarView} from "angular-calendar";
import {Subject} from "rxjs";
import {colors} from "@angular/cli/src/utilities/color";
import {AuthService} from "../auth.service";
import {ReunionService} from "../reunion.service";
import {Reunion} from "../reunion";

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



  constructor(private authService:AuthService,private reunionService:ReunionService) {
  }
  setView(view: CalendarView) {
    this.view = view;

  }

  ngOnInit(): void {
    this.reunionService.getReunionbyuserid(this.authService.getLoggedInUser().id_user).subscribe(reunions => {
      this.reunions = reunions;
      this.events = this.reunions.map(reunion => ({
        title: reunion.titre,
        start: new Date(reunion.debutR),
        end: new Date(reunion.finReu),
        id: reunion.id_Re
      }));
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
