import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {CinemaComponent} from "./cinema/cinema.component";
import { StaduimComponent } from './staduim/staduim.component';

const routes: Routes=[
  {
    path: 'cinema',
    component: CinemaComponent
  },
  {
    path: 'Staduim',
    component: StaduimComponent
  },
  { 
    path: '', 
    redirectTo: '/cinema', 
    pathMatch: 'full' 
  },
  { 
    path: '**', 
    redirectTo: '/cinema' 
  },
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ]
})
export class AppRoutingModule { }
