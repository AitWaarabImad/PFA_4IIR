import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {AppComponent} from "./app.component";
import {HomeComponent} from "./home/home.component";
import {ProfileComponent} from "./profile/profile.component";
import {AdminComponent} from "./admin/admin.component";
import {AuthGuard} from "./guards/auth.guard";
import {CreerReunionComponent} from "./creer-reunion/creer-reunion.component";
import {ReunionComponent} from "./reunion/reunion.component";
import {CalendrierComponent} from "./calendrier/calendrier.component";
import {CreersalledereunionComponent} from "./creersalledereunion/creersalledereunion.component";
import {SalledereunionComponent} from "./salledereunion/salledereunion.component";
import {EditRapportComponent} from "./edit-rapport/edit-rapport.component";
import {RapportComponent} from "./rapport/rapport.component";



const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'login' , component: LoginComponent},
  {path:"register" , component : RegisterComponent},
  {path:"profile" , component : ProfileComponent},
  {path:"admin" , component : AdminComponent, canActivate: [AuthGuard]},
  {path:"home" , component : HomeComponent},
  {path:"CreerReunion" , component :CreerReunionComponent},
  {path:"Reunion" , component : ReunionComponent},
  {path:"Calendrier" , component : CalendrierComponent},
  {path:"Creersalledereunion" , component : CreersalledereunionComponent,canActivate:[AuthGuard]},
  {path:"salledereunion" , component : SalledereunionComponent,canActivate:[AuthGuard]},
  {path:"creerrapport" , component : EditRapportComponent },
  {path:"rapport" , component : RapportComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
