import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-rapport',
  templateUrl: './rapport.component.html',
  styleUrl: './rapport.component.css'
})
export class RapportComponent implements OnInit{

  RappFormgroup!: FormGroup
  resume:string |null = null;
  correct:string |null = null;
  constructor(private fb:FormBuilder,private httpClient:HttpClient) {
  }

  ngOnInit() {
    this.RappFormgroup = this.fb.group({
      rapport: this.fb.control("")
    })
  }

  GetResume() {
    let url = 'https://api-inference.huggingface.co/models/facebook/bart-large-cnn';
    let httpHeaders = new HttpHeaders().set('Authorization','Bearer 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');
    let payload = {
      inputs : this.RappFormgroup.value.rapport
    };
    this.httpClient.post<any>(url,payload,{headers:httpHeaders})
      .subscribe({
        next: (resp) => {
          // Check if the response is in the expected format
          if (Array.isArray(resp) && resp.length > 0 && resp[0].hasOwnProperty('summary_text')) {
            this.resume = resp[0].summary_text; // Adjust this line based on actual response structure
          } else if (resp.hasOwnProperty('summary_text')) {
            this.resume = resp.summary_text; // Adjust if the response is a single object
          } else {
            this.resume = 'No summary available.';
          }
        },
        error: (err) => {
          console.error('Error from API:', err); // Debugging: log any error
          this.resume = 'An error occurred while fetching the summary.';
        }
      });
  }

  GetCorrection() {
    const url = 'https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=AIzaSyAHEkLuci3wa-pyFI1FnuTW3aqqGmMEHeI';
    const payload = {
      inputs: this.RappFormgroup.value.rapport
    };

    this.httpClient.post<any>(url, payload).subscribe({
      next: (resp) => {
        console.log('Response from API:', resp); // Debugging: log the entire response
        // Adjust this based on actual response structure
        if (resp && resp.correct) {
          this.correct = resp.correct;
        } else {
          this.correct = 'No corrected text available.';
        }
      },
      error: (err) => {
        console.error('Error from API:', err); // Debugging: log any error
        this.correct   = 'An error occurred while fetching the corrected text.';
      }
    });
  }
}
