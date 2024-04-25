import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {User} from "../user";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit{
  users: User[] =[];
  displayedColumns: string[] = ['name', 'email', 'role', 'actions'];
  roles: string[] = ['Admin', 'Invite', 'Organisateur', 'Rapporteur'];


  constructor(private authService : AuthService,private dialog: MatDialog) {
  }
  ngOnInit() {
    this.getUsers();
  }

  getUsers()
  {
    this.authService.getAllUsers().subscribe(
        (users: User[]) => {this.users = users;
          console.log('Users:', this.users);}
    )
  }
  editUser(user: User) {
    this.authService.UpdateRole(user).subscribe(
      () => {console.log("Modif reussite")},
      () => {console.log("erreur")}
    )
  }
  updateUserRole(user: User) {
    // You can perform any action here when the role is updated
    console.log('Updated role:', user.role);
  }

}
