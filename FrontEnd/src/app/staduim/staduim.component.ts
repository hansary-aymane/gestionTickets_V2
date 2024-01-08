import { Component, OnInit } from '@angular/core';
import { StadiumService } from '../services/stadium.service';

@Component({
  selector: 'app-staduim',
  templateUrl: './staduim.component.html',
  styleUrls: ['./staduim.component.css']
})
export class StaduimComponent implements OnInit{
  // @ts-ignore
  public villes;

  // @ts-ignore
  public stadiums;
  // @ts-ignore
  public stadiumsalle;

  // @ts-ignore
  public currentVille;

  // @ts-ignore
  public eventStadiumSalle;

  // @ts-ignore
  public currentStadium;
  // @ts-ignore
  public currentProjectionEvent;
  // @ts-ignore
  private ticket;

  // @ts-ignore
  public selectedTickets;
  constructor(public stadiumservice:StadiumService) {
  }
  ngOnInit(): void {
    this.stadiumservice.getVilles()
      .subscribe(data=>{
        this.villes=data;
      },error => {
        console.log(error);
      })
  }
  // @ts-ignore
  OnGetStadium(v): void {
    console.log("###############"+v.nom);
    this.currentVille=v;
    this.stadiumsalle=undefined;
    this.stadiumservice.getStadium(v)
      .subscribe(data=>{
        this.stadiums=data;
      },err => {
        console.log(err);
      })
  }
  // @ts-ignore
  OnGetSalle(st): void {
    this.currentStadium=st;
    this.stadiumservice.getSalle(st)
      .subscribe(data=>{
        this.stadiumsalle=data;
        // @ts-ignore
          this.stadiumservice.getEventSalleStadium(this.stadiumsalle)
            .subscribe(data=>{
              this.eventStadiumSalle=data;
              // @ts-ignore
              this.eventStadiumSalle._embedded.events.forEach(event => {
                console.log("#######" + event.titre);
              });
            },err => {
              console.log(err);
            })
        
      },err => {
        console.log(err);
      })
  }
}
