import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {AppComponent} from "./app.component";
import {HomeComponent} from "./home/home.component";
import {ProfileComponent} from "./profile/profile.component";
import {AdminComponent} from "./admin/admin.component";
import {AuthGuard} from "./guards/auth.guard";


const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'login' , component: LoginComponent},
  {path:"register" , component : RegisterComponent},
  {path:"profile" , component : ProfileComponent},
  {path:"admin" , component : AdminComponent, canActivate: [AuthGuard]},
  {path:"home" , component : HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
