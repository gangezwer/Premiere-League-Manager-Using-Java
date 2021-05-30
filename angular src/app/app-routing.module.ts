import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PointsortComponent } from './pointsort/pointsort.component';
import { HomeComponent } from './home/home.component';
import { WinsortComponent } from './winsort/winsort.component';
import { GoalsortComponent } from './goalsort/goalsort.component';
import { MatchsortComponent } from './matchsort/matchsort.component';
import { RandommatchComponent } from './randommatch/randommatch.component';
import { MatchfinderComponent } from './matchfinder/matchfinder.component';


const routes: Routes = [
    { path: 'pointsort', component: PointsortComponent },
    {path:'', component: HomeComponent },
    { path: 'winsort', component: WinsortComponent },
    { path: 'goalsort', component: GoalsortComponent },
    { path: 'matchsort', component: MatchsortComponent },
    { path: 'matchfinder', component: MatchfinderComponent },
    { path: 'randommatch', component: RandommatchComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
