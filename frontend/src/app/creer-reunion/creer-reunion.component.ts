import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import { ReunionService } from "../reunion.service";
import { AuthService } from "../auth.service";
import { CalendarEvent, CalendarView } from "angular-calendar";
import { Reunion } from "../reunion";
import { SallereunionService } from "../sallereunion.service";
import {MatSelectChange} from "@angular/material/select";
import {User} from "../user";

@Component({
  selector: 'app-creer-reunion',
  templateUrl: './creer-reunion.component.html',
  styleUrls: ['./creer-reunion.component.css']
})
export class CreerReunionComponent implements OnInit {
  protected readonly CalendarView = CalendarView;
  viewDate = new Date();
  isWeekViewVisible = false;
  events: CalendarEvent[] = [];
  reunions: Reunion[] = [];
  nomC!: any;

  nomm!: string;
  prenomm!: string;

  debutR!: Date;
  finReu!: Date;
  description!: string;
  nom_rapporteur!: string;
  nom_organisateur!: string;
  titre!: string;
  id_re!: any;
  id_rapp!:number;
  TodayDate: any = Date.now();

  controlRapp = new FormControl('',Validators.required);
  controlSalle = new FormControl('',Validators.required);
  controlInvite = new FormControl('',Validators.required);
  rapporteur: string[] = [];
  Salle: string[] = [];
  invites: User[] = [];

  constructor(
    private reunionService: ReunionService,
    private authService: AuthService,
    private salleService: SallereunionService
  ) {}

  ngOnInit() {
    this.authService.getnames().subscribe(names => {
      this.rapporteur = names;
    });
    this.authService.getnamesinvite().subscribe(invites => {
      this.invites = invites;
    });
    this.salleService.getAllsallenames().subscribe(sallesnom => {
      this.Salle = sallesnom;
    });
  }

  creerReunion() {
    const rapporteurValue = this.controlRapp.value;
    const salleValue = this.controlSalle.value;
    const inviteValue = this.controlInvite.value;
    const InviteValue: number[] = inviteValue ? (inviteValue as unknown as number[]) : [];
    const nouvelleReunion: Reunion = {
      id_Re: this.id_re,
      id_user: this.authService.getLoggedInUser().id_user,
      debutR: this.debutR,
      finReu: this.finReu,
      titre: this.titre,
      description: this.description,
      nom_rapporteur: rapporteurValue !== null ? rapporteurValue : " ",
      nom_salle: salleValue !== null ? salleValue : " ",
      nom_organisateur: this.authService.getLoggedInUser().nomComplet,
      ids:InviteValue,
      id_rapporteur:this.id_rapp,
    };

    this.reunionService.CreerReunion(nouvelleReunion).subscribe(() => {
      console.log(nouvelleReunion)
      console.log('Nouvelle réunion créée avec succès.');
      this.resetFields();
    }, (error) => {
      console.error('Erreur lors de la création de la réunion : ', error);
    });
  }

  onChange(value: any) {
    let today = new Date().getTime();
    let selected = new Date(value).getTime();
    if (selected < today) {
      alert("Problem");
      this.debutR = new Date();
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
  }

  pastDateTime() {
    let tDate: any = new Date();
    let date: any = tDate.getDate();
    if (date < 10) {
      date = "0" + date;
    }
    let year: any = tDate.getFullYear();
    let month: any = tDate.getMonth() + 1;
    if (month < 10) {
      month = "0" + month;
    }
    let hours: any = tDate.getHours();
    if (hours < 10) {
      hours = "0" + hours;
    }
    let minutes: any = tDate.getMinutes();
    if (minutes < 10) {
      minutes = "0" + minutes;
    }
    this.TodayDate = year + "-" + month + "-" + date + "T" + hours + ":" + minutes;
    console.log(this.TodayDate);
  }

  click() {
    console.log(this.controlInvite.value)

  }
}
