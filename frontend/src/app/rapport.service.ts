import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Rapport} from "./rapport";

@Injectable({
  providedIn: 'root'
})
export class RapportService {

  private baseUrl = "http://localhost:8086";

  constructor(private httpClient:HttpClient) { }

  getReuofuser(id:number):Observable<any>
  {
    return this.httpClient.get(`${this.baseUrl}/rapports/${id}`);
  }

  getRapportById(rapportId: number):Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/rapport/${rapportId}`);

  }


  updateRapport(id_rapport: number, rapportData: Rapport) {
    return this.httpClient.put(`${this.baseUrl}/update/${id_rapport}`,rapportData);
  }
}
