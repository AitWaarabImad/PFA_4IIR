import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
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
  getReunionbyrappid(id_user:any):Observable<any>
  {
    return this.http.get(`${this.baseUrl}/rappReunions/${id_user}`,id_user)
  }

  DeleteReunion(id_reu:any):Observable<any>
  {
    return  this.http.delete(`${this.baseUrl}/delete/${id_reu}`)
  }
  getAllreunions():Observable<any>
  {
    return this.http.get(`${this.baseUrl}/AllReunions`)
  }

  getReubyid(ids: number[]): Observable<any> {
    const params = new HttpParams().set('ids', ids.join(','));
    return this.http.get(`${this.baseUrl}/reunions`, { params });
  }
  getReuid(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/reunions/${id}`);
  }


}
