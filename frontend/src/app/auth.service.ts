import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedInUserSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  public loggedInUser$: Observable<any> = this.loggedInUserSubject.asObservable();
  isLoggedin : boolean = false;
  UserRole : any ;

  private baseUrl = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  public register(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, user);
  }
  public login(user: { username: string, password: string }):Observable<Object>
  {
    this.isLoggedin = true;
    return this.http.post(`${this.baseUrl}/login`,user)
  }
  public setLoggedInUser(user: any) {
    this.loggedInUserSubject.next(user);
    // You can also store user data in local storage here for persistence
  }

  public getLoggedInUser(): any {
    if(this.loggedInUserSubject.value == null)
    {
      this.isLoggedin = false;
    }
    return this.loggedInUserSubject.value;
  }
  public getRoleuser():any {
    const user = this.getLoggedInUser()
    return user ? user.role : '';
  }

  public logout() {
    this.loggedInUserSubject.next(null);

    // You might also want to clear user data from local storage here
  }

  public getAllUsers():any
  {
    return this.http.get(`${this.baseUrl}/all`);
  }
  public UpdateRole(user : any):any
  {
    return this.http.put(`${this.baseUrl}/update`,user);
  }

  public getnameById(id:any):any
  {
    return this.http.get(`${this.baseUrl}/getnameId/${id}`,{responseType: 'text'})
  }
  public getuserById(id:any):any
  {
    return this.http.get(`${this.baseUrl}/getId/${id}`,id)
  }

}
