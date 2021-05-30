import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-pointsort',
  templateUrl: './pointsort.component.html',
  styleUrls: ['./pointsort.component.css']
})
export class PointsortComponent implements OnInit {

   clubs: any;

  constructor(private http: HttpClient) { }



  ngOnInit(): void {
        this.http.get("http://localhost:8080/pointComparator").subscribe((data) =>
        {console.log("recieved")
        console.log(data);
        this.clubs = data;
   });
  }
}
