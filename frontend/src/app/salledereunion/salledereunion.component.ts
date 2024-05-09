import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "../auth.service";
import {MatDialog} from "@angular/material/dialog";
import {User} from "../user";
import {MatTableDataSource} from "@angular/material/table";
import {SallereunionService} from "../sallereunion.service";
import {MatPaginator} from "@angular/material/paginator";
import {Sallereunion} from "../sallereunion";

@Component({
  selector: 'app-salledereunion',
  templateUrl: './salledereunion.component.html',
  styleUrl: './salledereunion.component.css'
})
export class SalledereunionComponent implements OnInit{


  salle: Sallereunion[] =[];
  displayedColumns: string[] = ['id_salle', 'nomSalle', 'place', 'projecteur'];

  dataSource!: MatTableDataSource<Sallereunion, MatPaginator>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(private salleService: SallereunionService, private dialog: MatDialog) {}

  ngOnInit() {
    this.getsalle();
  }
  getsalle() {
    this.salleService.getAllsalle().subscribe(
      (salle: Sallereunion[]) => {
        this.salle = salle;
        this.dataSource = new MatTableDataSource(this.salle);
        this.dataSource.paginator = this.paginator; // Assign paginator to the data source
      },
      (error: any) => {
        console.error('Error fetching salles:', error);
      }
    );
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.dataSource.filter = filterValue;
  }

  editSalle() {

  }
}
