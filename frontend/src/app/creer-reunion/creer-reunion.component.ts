import {Component, input, NgZone, OnInit, ViewChild} from '@angular/core';
import {ReunionService} from "../reunion.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Reunion} from "../reunion";
import {AuthService} from "../auth.service";
import {CalendarEvent, CalendarView} from "angular-calendar";
import {map, Observable, startWith, switchMap} from "rxjs";
import {User} from "../user";
import {SallereunionService} from "../sallereunion.service";



@Component({
  selector: 'app-creer-reunion',
  templateUrl: './creer-reunion.component.html',
  styleUrls: ['./creer-reunion.component.css']
})
export class CreerReunionComponent implements OnInit{
  protected readonly CalendarView = CalendarView;
  viewDate = new Date();
  isWeekViewVisible = false;
  events: CalendarEvent[] = [];
  reunions : Reunion[] = [];
  nomC!: any;

  nomm!: string;
  prenomm!: string;

  debutR!: Date;
  finReu!: Date;
  description!: string;
  nom_rapporteur!: string;
  nom_organisateur!: string;
  titre!:string;
  id_re!:any;
  TodayDate : any = Date.now();

  controlRapp = new FormControl('');
  controlSalle = new FormControl('');
  rapporteur: string[] = [];
  Salle: string[] = [];
  filteredRapporteur!: Observable<string[]>;
  filteredSalle!: Observable<string[]>;



  constructor(private reunionService: ReunionService,private authService: AuthService,private salleService:SallereunionService) {}


  ngOnInit() {
    this.filteredRapporteur = this.controlRapp.valueChanges.pipe(
      startWith(''),
      map(value => this._filterRapporteur(value || '')),
    );
    this.filteredSalle = this.controlSalle.valueChanges.pipe(
      startWith(''),
      map(value => this._filterSalle(value || '')),
    );
    this.authService.getnames().subscribe(names =>
    {
      this.rapporteur = names;
    })
    this.salleService.getAllsallenames().subscribe(sallesnom =>
    {
      this.Salle = sallesnom;
    })

  }



  creerReunion() {
    const rapporteurValue = this.controlRapp.value;
    const salleValue = this.controlSalle.value;
    const nouvelleReunion: Reunion = {
      id_Re: this.id_re,
      id_user: this.authService.getLoggedInUser().id_user,
      debutR: this.debutR,
      finReu: this.finReu,
      titre:this.titre,
      description: this.description,
      nom_rapporteur: rapporteurValue !== null ? rapporteurValue : " ",
      nom_salle:salleValue !== null ? salleValue : " ",
      nom_organisateur:this.authService.getLoggedInUser().nomComplet,
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
  }

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



  private _filterRapporteur(value: string): string[] {
    const filterValue = this._normalizeValue(value);
    return this.rapporteur.filter(rapporteur => this._normalizeValue(rapporteur).includes(filterValue));
  }
  private _filterSalle(value: string): string[] {
    const filterValue = this._normalizeValue(value);
    return this.Salle.filter(Salle => this._normalizeValue(Salle).includes(filterValue));
  }

  private _normalizeValue(value: string): string {
    return value.toLowerCase().replace(/\s/g, '');
  }



}


