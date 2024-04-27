import {Component, NgZone, OnInit, ViewChild} from '@angular/core';
import {ReunionService} from "../reunion.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Reunion} from "../reunion";



@Component({
  selector: 'app-creer-reunion',
  templateUrl: './creer-reunion.component.html',
  styleUrls: ['./creer-reunion.component.css']
})
export class CreerReunionComponent {
  reunionForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private reunionService: ReunionService
  ) {
    this.reunionForm = this.formBuilder.group({
      debutR: [''],
      finReu: [''],
      description: [''],
      rapport: ['']
    });
  }

  creerReunion() {
    const nouvelleReunion: Reunion = this.reunionForm.value;
    this.reunionService.CreerReunion(nouvelleReunion).subscribe(() => {
      console.log('Nouvelle réunion créée avec succès.');
      this.reunionForm.reset(); // Réinitialiser le formulaire après la création de la réunion
    }, (error) => {
      console.error('Erreur lors de la création de la réunion : ', error);
    });
  }
}


