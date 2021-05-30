import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-randommatch',
  templateUrl: './randommatch.component.html',
  styleUrls: ['./randommatch.component.css']
})
export class RandommatchComponent implements OnInit {

  clubs: any;

  constructor( private http: HttpClient ) { }

  ngOnInit(): void {

    }

    genrate(){
      this.http.get("http://localhost:8080/randomMatch").subscribe((data) =>
                {console.log("recieved")
                console.log(data);
                this.clubs = data;
           });
    }

}
