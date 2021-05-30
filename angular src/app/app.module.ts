import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { PointsortComponent } from './pointsort/pointsort.component';
import { HomeComponent } from './home/home.component';
import { WinsortComponent } from './winsort/winsort.component';
import { GoalsortComponent } from './goalsort/goalsort.component';
import { MatchsortComponent } from './matchsort/matchsort.component';
import { RandommatchComponent } from './randommatch/randommatch.component';
import { MatchfinderComponent } from './matchfinder/matchfinder.component';
import { FormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PointsortComponent,
    HomeComponent,
    WinsortComponent,
    GoalsortComponent,
    MatchsortComponent,
    RandommatchComponent,
    MatchfinderComponent,

  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
