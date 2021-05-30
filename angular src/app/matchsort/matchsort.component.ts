import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-matchsort',
  templateUrl: './matchsort.component.html',
  styleUrls: ['./matchsort.component.css']
})
export class MatchsortComponent implements OnInit {

  clubs:any;

  constructor(private http: HttpClient) { }

   ngOnInit(): void {
             this.http.get("http://localhost:8080/dateComparator").subscribe((data) =>
             {console.log("recieved")
             console.log(data);
             this.clubs = data;
        });
   }

}
