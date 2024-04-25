import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "../auth.service";
import {User} from "../user";
import {MatDialog} from "@angular/material/dialog";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit{
  users: User[] =[];
  displayedColumns: string[] = ['name', 'email', 'role', 'actions'];
  roles: string[] = ['Admin', 'Invite', 'Organisateur', 'Rapporteur'];

  dataSource!: MatTableDataSource<User>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private authService: AuthService, private dialog: MatDialog) {}

  ngOnInit() {
    this.getUsers();
  }
  getUsers() {
    this.authService.getAllUsers().subscribe(
      (users: User[]) => {
        this.users = users;
        this.dataSource = new MatTableDataSource(this.users);
        this.dataSource.paginator = this.paginator; // Assign paginator to the data source
      },
      (error: any) => {
        console.error('Error fetching users:', error);
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

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.dataSource.filter = filterValue;
  }

}
