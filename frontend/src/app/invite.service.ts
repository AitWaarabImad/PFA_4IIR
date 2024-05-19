import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class InviteService {


  private baseUrl = "http://localhost:8083/invites";

  constructor(private http:HttpClient) { }

  getIdsofReu(id:number):Observable<any>
  {
    return this.http.get(`${this.baseUrl}/reunions/${id}`)
  }

}
