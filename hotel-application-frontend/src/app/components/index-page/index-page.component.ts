import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../shared-components/header/header.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LocationService } from '../services/location.service';
import { LocationInterface } from '../models/location.model';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { HotelService } from '../services/hotel.service';
import { HotelInterface } from '../models/hotel.model';
import { UserService } from '../services/user.service';
import { ReservationInterface } from '../models/reservation.model';
import { ReservationService } from '../services/reservation.service';
import { RoomInterface } from '../models/room.model';

@Component({
  selector: 'app-index-page',
  standalone: true,
  imports: [HeaderComponent, CommonModule, RouterModule, FormsModule, ReactiveFormsModule],
  templateUrl: './index-page.component.html',
  styleUrl: './index-page.component.css'
})
export class IndexPageComponent implements OnInit {
  userLocation: LocationInterface = {
    latitude: 0,
    longitude: 0
  }
  foundHotels: HotelInterface[] = [];
  reservedRoom: ReservationInterface[] = [];
  reservedHotel: HotelInterface[] = [];
  
  roomTypes: string[] = ['Single Room', 'Double Room', 'Matrimonial Room'];
  errorMessages: string[] = [];
  successMessages: string[] = [];
  searchForm: FormGroup = new FormGroup({});
  changeRoomForm: FormGroup = new FormGroup({});
  formSubmited: boolean = false;
  constructor(private locationService: LocationService, private formBuilder: FormBuilder, private hotelService: HotelService, public userService: UserService, private reservationService: ReservationService) {

  }

  ngOnInit(): void {
    this.locationService.getPosition().then(position => {
      this.userLocation = position;
    });
    this.initializeForm();
    this.initializeReservations();
    this.changeRoomForm = this.formBuilder.group({
      roomID: [0, [Validators.required]]
    })
  }

  initializeForm() {
    this.searchForm = this.formBuilder.group({
      searchDistance: [null, [Validators.required]]
    })
  }

  initializeReservations() {
    this.reservationService.getUserReservations(1).subscribe({
      next: value => {
        this.reservedRoom = value;
      }
    })
    this.reservedRoom.forEach(reserved => {
      this.reservedHotel.push(reserved.hotel);
    })
  }

  getHotel(rooms: RoomInterface[]) {
    return rooms.filter(room => room.available);
  }

  searchForHotels() {
    this.formSubmited = true;
    if(this.searchForm.valid) {
      this.hotelService.getAreaHotels(this.searchForm.value.searchDistance, this.userLocation).subscribe({
        next: (value) => {
          this.foundHotels = value;
        },
      });
    } else {
      this.errorMessages.pop();
      this.errorMessages.push("Campul trebuie completat obligatoriu.");
    }
  }

  cancelReservation(reservationID: number) {
    this.reservationService.deleteReservation(reservationID).subscribe({
      next: (value) => {
        this.successMessages.pop();
        let message: any = value;
        this.successMessages.push(message.message);
        this.initializeReservations();
      },
      error: (value) => {
        console.log(value);
        this.errorMessages.pop();
        this.errorMessages.push(value.error.message);
      }
    })
  }

  changeRoom(reservationID: number) {
    if(this.changeRoomForm.valid && this.changeRoomForm.value.roomID > 0) {
      this.reservationService.changeReservatedRoom(reservationID, this.changeRoomForm.value.roomID).subscribe({
        next: (value) => {
          this.initializeReservations();
          this.changeRoomForm.reset();
        },
        error: (err) => {
          console.log(err);
          this.errorMessages.pop();
          this.errorMessages.push(err.error.message);
        },
      })
    } else {
      this.errorMessages.pop();
      this.errorMessages.push("Trebuie sa alegi o camera!");
    }
  }
  
}
