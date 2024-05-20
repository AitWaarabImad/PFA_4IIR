import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../auth.service";
import {RapportService} from "../rapport.service";
import {Rapport} from "../rapport";
import {ReunionService} from "../reunion.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-rapport',
  templateUrl: './rapport.component.html',
  styleUrl: './rapport.component.css'
})
export class RapportComponent implements OnInit{

  rapport :Rapport [] = [];
  constructor(private authService:AuthService,private rapportService:RapportService,private reunionService:ReunionService,private router:Router) {
  }
  ngOnInit(): void {
    const id_user = this.authService.getLoggedInUser().id_user;

    this.rapportService.getReuofuser(id_user).subscribe({
      next: resp =>
        this.rapport = resp,
      error : err => console.error('Failed to fetch rapports by user ID', err)
    })
  }

  getnameofreu(id:number)
  {
    let name:string=" "
    this.reunionService.getReuid(id).subscribe({next:resp=>
      {
        name=resp;
      }})
    return name
  }


  editRapport(id_rapport: number) {
    this.router.navigate(['/creerrapport'], { queryParams: { id: id_rapport } });  }
}
