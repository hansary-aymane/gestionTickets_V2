import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class StadiumService {

  public host:string="http://localhost:8080"
  constructor(private http:HttpClient) { }

  public getVilles(){
    return this.http.get(this.host+"/villes");
  }

  // @ts-ignore
  getStadium(st) {
    return this.http.get(st._links.stadiums.href);
  }
  // @ts-ignore
  getSalle(st) {
    console.log(this.http.get(st._links.stadiumSalle.href));
    return this.http.get(st._links.stadiumSalle.href);
  }
  // @ts-ignore
  getEventSalleStadium(stadiumsalle){
    return this.http.get(stadiumsalle._links.events.href)
  }
}