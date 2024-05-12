import {Component, input, NgZone, OnInit, ViewChild} from '@angular/core';
import {ReunionService} from "../reunion.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Reunion} from "../reunion";
import {AuthService} from "../auth.service";
import {CalendarEvent, CalendarView} from "angular-calendar";



@Component({
  selector: 'app-creer-reunion',
  templateUrl: './creer-reunion.component.html',
  styleUrls: ['./creer-reunion.component.css']
})
export class CreerReunionComponent {
  protected readonly CalendarView = CalendarView;
  viewDate = new Date();
  isWeekViewVisible = false;
  events: CalendarEvent[] = [];
  reunions : Reunion[] = [];

  debutR!: Date;
  finReu!: Date;
  description!: string;
  id_rapporteur!: any;
  id_re!:any;
  TodayDate : any = Date.now();

  pastDateTime(){
    let tDate:any = new Date();
    let date:any  = tDate.getDate();
    if(date<10)
    {
      date = "0" + date;
    }
    let year:any = tDate.getFullYear();
    let month:any  = tDate.getMonth()+1;
    if(month<10)
    {
      month = "0"+month;
    }
    let hours:any  = tDate.getHours();
    if(hours<10)
    {
      hours = "0"+hours;
    }
    let minutes:any  = tDate.getMinutes();
    if(minutes<10)
    {
      minutes = "0"+minutes;
    }
    this.TodayDate = year+"-"+month+"-"+date+"T"+hours+":"+minutes;
    console.log(this.TodayDate)
  }


  constructor(private reunionService: ReunionService,private authService: AuthService) {}

  creerReunion() {
    const nouvelleReunion: Reunion = {
      id_Re: this.id_re,
      id_user: this.authService.getLoggedInUser().id_user,
      debutR: this.debutR,
      finReu: this.finReu,
      description: this.description,
      id_rapporteur:this.id_rapporteur,
    };

    this.reunionService.CreerReunion(nouvelleReunion).subscribe(() => {
      console.log('Nouvelle réunion créée avec succès.');
      this.resetFields();
    }, (error) => {
      console.error('Erreur lors de la création de la réunion : ', error);
    });
  }

  onChange(value:any) {
    let today = new Date().getTime();
    let selected = new Date(value).getTime();
    if(selected<today)
    {
          alert("Problem");
      this.debutR=new Date();
    }
    this.reunionService.getAllreunions().subscribe(reunions => {
      this.reunions = reunions;
      this.events = this.reunions.map(reunion => ({
        title: reunion.description,
        start: new Date(reunion.debutR),
        end: new Date(reunion.finReu),
        id: reunion.id_Re
      }));
    });
    this.isWeekViewVisible = true;


  }


  private resetFields() {
    this.debutR = new Date();
    this.finReu = new Date();
    this.description = '';
    this.id_rapporteur = '';
  }

}


