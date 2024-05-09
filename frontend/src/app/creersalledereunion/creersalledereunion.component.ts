import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {SallereunionService} from "../sallereunion.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-creersalledereunion',
  templateUrl: './creersalledereunion.component.html',
  styleUrl: './creersalledereunion.component.css'
})
export class CreersalledereunionComponent implements OnInit{

  salleFormgroup!: FormGroup;
  constructor(private fb:FormBuilder,private authservice:AuthService,private sallereunionservice:SallereunionService) {
    this.salleFormgroup = this.fb.group({
      nomSalle: [null, Validators.required],
      place : [null, Validators.required],
      projecteur: [null, Validators.required],

    })
  }

  ngOnInit() {

  }

  creersalle() {
    if (this.salleFormgroup.invalid) {
      return;
    }

    const salle = {
      nomSalle: this.salleFormgroup.value.nomSalle,
      place: this.salleFormgroup.value.place,
      projecteur: this.salleFormgroup.value.projecteur,
    };
    this.sallereunionservice.CreerSallereunion(salle).subscribe()
  }
}
