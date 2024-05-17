import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HotelInterface } from '../models/hotel.model';
import { Observable } from 'rxjs';
import { LocationInterface } from '../models/location.model';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  constructor(private http: HttpClient) { }

  getAllHotels(): Observable<HotelInterface[]> {
    return this.http.get<HotelInterface[]>(environment.apiUrl + "api/hotels/get-all-hotels");
  }

  getHotel(id: number): Observable<HotelInterface> {
    return this.http.get<HotelInterface>(environment.apiUrl + "api/hotels/get-hotel/" + id);
  }

  getAreaHotels(distance: number, coords: LocationInterface): Observable<HotelInterface[]> {
    return this.http.get<HotelInterface[]>(environment.apiUrl + "api/hotels/get-all-area-hotels?distance=" + distance + "&latitude=" + coords.latitude + "&longitude=" + coords.longitude);
  }
}
