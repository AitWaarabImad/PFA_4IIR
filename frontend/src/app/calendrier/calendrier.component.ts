import { Component } from '@angular/core';
import {CalendarEvent, CalendarView} from "angular-calendar";
import {Subject} from "rxjs";
import {colors} from "@angular/cli/src/utilities/color";

@Component({
  selector: 'app-calendrier',
  templateUrl: './calendrier.component.html',
  styleUrl: './calendrier.component.css'
})
export class CalendrierComponent {

  view: CalendarView = CalendarView.Month;

  viewDate = new Date();

  events: CalendarEvent[] = [];

  protected readonly CalendarView = CalendarView;

  setView(view: CalendarView) {
    this.view = view;

  }
}
