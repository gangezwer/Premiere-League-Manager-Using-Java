import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-matchfinder',
  templateUrl: './matchfinder.component.html',
  styleUrls: ['./matchfinder.component.css']
})

export class MatchfinderComponent implements OnInit {

   temp:any;
   clubs:any;
   searchDate: string = '';

  constructor( private http: HttpClient ) {}


   ngOnInit(): void {
             this.http.get("http://localhost:8080/matchFinder").subscribe((data: any) =>
             {console.log("recieved")
             console.log(data);
             this.clubs = data[0];
             this.temp = data[0];
        });
   }

   searchByDate(){
     if (this.searchDate === '' || this.searchDate === ' ') {
         this.clubs = this.temp;
       }
       this.clubs = [];
       this.temp.forEach((val: any) => {
         if (val.date.toString().includes(this.searchDate)) {
           this.clubs.push(val);
         }
       })
    }
}

