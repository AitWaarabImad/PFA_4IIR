import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Reunion} from "./reunion";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReunionService {

  private baseUrl = "http://localhost:8085";

  constructor(private http:HttpClient) { }

  CreerReunion(reunion:Reunion):Observable<any>
  {
    return this.http.post(`${this.baseUrl}/createReunion`,reunion)
  }
  getReunionbyuserid(id_user:any):Observable<any>
  {
    return this.http.get(`${this.baseUrl}/userReunions/${id_user}`,id_user)
  }

  DeleteReunion(id_reu:any):Observable<any>
  {
    return  this.http.delete(`${this.baseUrl}/delete/${id_reu}`)
  }
  getAllreunions():Observable<any>
  {
    return this.http.get(`${this.baseUrl}/AllReunions`)
  }
}
