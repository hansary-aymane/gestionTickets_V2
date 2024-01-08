import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CinemaService} from "../services/cinema.service";

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit{
  // @ts-ignore
  public villes;

  // @ts-ignore
  public cinemas;
  // @ts-ignore
  public salles;

  // @ts-ignore
  public currentVille;

  // @ts-ignore
  public currentCinema;
  // @ts-ignore
  public currentProjection;
  // @ts-ignore
  private ticket;

  // @ts-ignore
  public selectedTickets;
  constructor(public cinemaservice:CinemaService) {
  }
  ngOnInit(): void {
    console.log("###############ngoninit")
    this.cinemaservice.getVilles()
      .subscribe(data=>{
        this.villes=data;
      },error => {
        console.log(error);
      })
  }

  // @ts-ignore
  OnGetCinema(v): void {
    this.currentVille=v;
    this.salles=undefined;
    this.cinemaservice.getCinemas(v)
      .subscribe(data=>{
        this.cinemas=data;
      },err => {
        console.log(err);
      })

  }
  // @ts-ignore
  OnGetSalles(c): void {
    this.currentCinema=c;
    this.cinemaservice.getSalles(c)
      .subscribe(data=>{
        this.salles=data;
        // @ts-ignore
        this.salles._embedded.salles.forEach(salle=>{
          this.cinemaservice.getProjections(salle)
            .subscribe(data=>{
              salle.projections=data;
            },err => {
              console.log(err);
            })
        })
      },err => {
        console.log(err);
      })

  }

  // @ts-ignore
  OnGetTicketPlace(p) {
    this.currentProjection=p;
    this.cinemaservice.getTicketsPlaces(p)
      .subscribe(data=>{
        this.currentProjection.tickets=data;
        this.selectedTickets=[];
      },err => {
        console.log(err);
      })

  }
  // @ts-ignore
  onSelectTicket(t){
    if(!t.selected){
      t.selected = true;
      this.selectedTickets.push(t);
    }
    else{
      t.selected = false;
      this.selectedTickets.splice(this.selectedTickets.indexOf(t), 1);
    }
  }
  // @ts-ignore
  getTicketClass(t){
    let str="btn ticket ";
    if(t.reservee){
      str += "btn-danger";
    }
    else if(t.selected){
      str += "btn-warning";
    }else{
      str += "btn-success";
    }
    return str;
  }
  /*
  // @ts-ignore

  onSelectTicket(t){
    console.log(JSON.stringify(t.selected)+" OnselectTicket");
    if(!t.selected){
      t.selected=true;
      this.selectedTickets.push(t);
    }
    t.selected=false;
    this.selectedTickets.splice(this.selectedTickets.indexOf(t),1);
  }

  // @ts-ignore

  getTicketClass(t) {
    let str="btn ";
    if(t.reserve==true){
      str+="btn-danger";
    }
    else if(t.selected){
      str+="btn-warning"
    }
    else{
      str+="btn-success"
    }
    return str;
  }
*/
  // @ts-ignore
  OnPayTicket(formulaire) {
    // @ts-ignore
    let ticket=[];
    // @ts-ignore
    this.selectedTickets.forEach(s=>{
      ticket.push(s.id)
    })
    // @ts-ignore
    formulaire.tickets=ticket;
    this.cinemaservice.payerTickets(formulaire)
      .subscribe(data=>{
        alert("votre reservation a reussi")
        this.OnGetTicketPlace(this.currentProjection); // recharger la projection courante apres mod
      },err => {
        console.log(err);
      })
  }
}
