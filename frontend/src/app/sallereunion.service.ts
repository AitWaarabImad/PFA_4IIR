import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Reunion} from "./reunion";
import {Observable} from "rxjs";
import {Sallereunion} from "./sallereunion";

@Injectable({
  providedIn: 'root'
})
export class SallereunionService {

  private baseUrl = "http://localhost:8087";

  constructor(private http:HttpClient) {}

  CreerSallereunion(salle: { place: any; projecteur: any; nomSalle: any }):Observable<any>
  {
    return this.http.post(`${this.baseUrl}/createSalle`,salle)
  }


  getAllsalle():any {
    return this.http.get(`${this.baseUrl}/allsalle`)
  }
}
