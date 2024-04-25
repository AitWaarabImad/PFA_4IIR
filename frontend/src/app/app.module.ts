import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from "@angular/common/http";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatToolbar, MatToolbarModule} from "@angular/material/toolbar";
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {MatButton, MatButtonModule} from "@angular/material/button";
import {MatFormField, MatFormFieldModule, MatLabel} from "@angular/material/form-field";
import {MatInput, MatInputModule} from "@angular/material/input";
import {MatCard, MatCardContent, MatCardHeader, MatCardModule, MatCardTitle} from "@angular/material/card";
import {FlexLayoutModule, FlexModule} from "@angular/flex-layout";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { NavbarComponent } from './navbar/navbar.component';
import {MatNavList} from "@angular/material/list";
import { AdminComponent } from './admin/admin.component';
import {MatCell, MatHeaderCell, MatHeaderRow, MatRow, MatTable, MatTableModule} from "@angular/material/table";
import {MatOption} from "@angular/material/autocomplete";
import {MatSelect} from "@angular/material/select";
import {AuthGuard} from "./guards/auth.guard";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    NavbarComponent,
    AdminComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        MatSlideToggleModule,
        MatToolbarModule,
        MatButtonModule,
        MatLabel,
        MatFormFieldModule,
        MatInputModule,
        MatCardModule,
        MatCardTitle,
        MatCardHeader,
        MatCardContent,
        FlexLayoutModule,
        FormsModule,
        ReactiveFormsModule,
        MatIconModule,
        MatNavList,
        MatHeaderCell,
        MatCell,
        MatHeaderRow,
        MatRow,
        MatTableModule,
        MatOption,
        MatSelect,
        MatPaginatorModule
    ],
  providers: [
    provideAnimationsAsync(),AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
