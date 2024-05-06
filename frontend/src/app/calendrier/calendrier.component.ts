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

  reunions!: Reunion[]
  viewDate = new Date();

  events: CalendarEvent[] = [];

  protected readonly CalendarView = CalendarView;


  constructor(private authService:AuthService,private reunionService:ReunionService) {
  }
  setView(view: CalendarView) {
    this.view = view;

  }

  ngOnInit(): void {
    this.reunionService.getReunionbyuserid(this.authService.getLoggedInUser().id_user).subscribe(reunions => {
      this.reunions = reunions;
      this.events.push(reunions);
    }
    )
  }
}
