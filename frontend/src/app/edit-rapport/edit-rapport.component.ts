import {Component, OnInit} from '@angular/core';
import {RapportService} from "../rapport.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Rapport} from "../rapport";

@Component({
  selector: 'app-edit-rapport',
  templateUrl: './edit-rapport.component.html',
  styleUrl: './edit-rapport.component.css'
})
export class EditRapportComponent implements OnInit {
  rapportId!: number;
  rapportData!: Rapport;

  constructor(private route: ActivatedRoute, private rapportService: RapportService,private router:Router) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.rapportId = +params['id'];
      this.fetchRapportData();
    });
  }

  fetchRapportData() {
    this.rapportService.getRapportById(this.rapportId).subscribe(
      (data: Rapport) => {
        this.rapportData = data;
      },
      (error: any) => {
        console.error('Error fetching rapport data:', error);
      }
    );
  }

  saveChanges() {
    // Call your RapportService method to save changes
    this.rapportService.updateRapport(this.rapportId, this.rapportData).subscribe(
      () => {
        console.log('Rapport updated successfully:');
        this.router.navigateByUrl("/rapport")
        // Optionally, perform any additional actions after saving changes
      },
      (error: any) => {
        console.error('Error saving changes:', error);
      }
    );
  }
}
