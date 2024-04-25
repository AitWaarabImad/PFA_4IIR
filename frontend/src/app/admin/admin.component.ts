import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "../auth.service";
import {User} from "../user";
import {MatDialog} from "@angular/material/dialog";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit{
  users: User[] =[];
  displayedColumns: string[] = ['name', 'email', 'role', 'actions'];
  roles: string[] = ['Admin', 'Invite', 'Organisateur', 'Rapporteur'];
  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private authService : AuthService,private dialog: MatDialog) {
  }
  ngOnInit() {
    this.getUsers();
  }

  getUsers()
  {
    this.authService.getAllUsers().subscribe(
        users => {this.users = users;this.users.paginator = this.paginator;
          }
    );

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
