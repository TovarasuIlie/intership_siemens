import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { ReservationInterface } from '../models/reservation.model';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  addReservation(userID: number, roomID: number, hotelID: number) {
    return this.http.get<ReservationInterface>(environment.apiUrl + "api/reservations/add-reservation/" + userID + "/" + roomID + "/" + hotelID);
  }

  getUserReservations(userID: number) {
    return this.http.get<ReservationInterface[]>(environment.apiUrl + "api/reservations/get-user-reservations/" + userID);
  }

  deleteReservation(reservationID: number) {
    return this.http.delete(environment.apiUrl + "api/reservations/cancel-room-reservation/" + reservationID);
  }

  changeReservatedRoom(reservationID: number, roomID:number) {
    return this.http.put(environment.apiUrl + "api/reservations/change-room-reservation/" + reservationID + "/" + roomID, {});
  }
}
