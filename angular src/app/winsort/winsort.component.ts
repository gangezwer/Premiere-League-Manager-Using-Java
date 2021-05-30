import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-winsort',
  templateUrl: './winsort.component.html',
  styleUrls: ['./winsort.component.css']
})
export class WinsortComponent implements OnInit {

  clubs: any;

  constructor( private http: HttpClient ) { }

   ngOnInit(): void {
          this.http.get("http://localhost:8080/winComparator").subscribe((data) =>
          {console.log("recieved")
          console.log(data);
          this.clubs = data;
     });
    }

}
