import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../shared-components/header/header.component';
import { HotelService } from '../services/hotel.service';
import { HotelInterface } from '../models/hotel.model';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ReservationService } from '../services/reservation.service';
import { FeedbackDTO, FeedbackInterface } from '../models/feedback.model';
import { FeedbackService } from '../services/feedback.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-hotel-details-page',
  standalone: true,
  imports: [CommonModule, HeaderComponent, RouterModule, FormsModule, ReactiveFormsModule],
  templateUrl: './hotel-details-page.component.html',
  styleUrl: './hotel-details-page.component.css'
})
export class HotelDetailsPageComponent implements OnInit {

  hotel: HotelInterface = {
    id: 0,
    name: '',
    latitude: 0,
    longitude: 0,
    rooms: []
  }

  feedbacks: FeedbackInterface[] = [];
  feedbackForm: FormGroup = new FormGroup({});

  avaibleRooms: number = 0;
  roomTypes: string[] = ['Single Room', 'Double Room', 'Matrimonial Room'];
  errorMessages: string[] = [];

  constructor(private hotelService: HotelService, private router: ActivatedRoute, private reservationService: ReservationService, private feedbackService: FeedbackService, private formBuilder: FormBuilder) {
    this.hotel.id = parseInt(this.router.snapshot.paramMap.get('id') || '');
    console.log(this.roomTypes[1]);
  }

  ngOnInit(): void {
    this.initializeHotel();
    this.initializeFeedbacks();
    this.initializeForm();
  }

  initializeHotel() {
    this.hotelService.getHotel(this.hotel.id).subscribe({
      next: (value) => {
        this.hotel = value;
        this.countAvailableRooms();
      },
    });
  }

  initializeFeedbacks() {
    this.feedbackService.getAllFeedbacks(this.hotel.id).subscribe({
      next: (value) => {
        this.feedbacks = value;
      }
    })
  }

  initializeForm() {
    this.feedbackForm = this.formBuilder.group({
      feedback: [null, [Validators.required]]
    })
  }

  get availableRooms() {
    return this.hotel.rooms.filter(room => room.available);
  }

  countAvailableRooms() {
    this.hotel.rooms.forEach(room => {
      if(room.available) {
        this.avaibleRooms++;
      }
    });
  }

  reserveRoom(userID: number, roomID: number) {
    this.reservationService.addReservation(userID, roomID, this.hotel.id).subscribe({
      next: (response) => {
        console.log(response);
        this.initializeHotel();
      },
      error: (response) => {
        console.log(response);
      }
    })
  }

  postFeedback() {
    if(this.feedbackForm.valid) {
      const feedbackDTO: FeedbackDTO = {
        userID: 1,
        hotelID: this.hotel.id,
        feedback: this.feedbackForm.value.feedback
      }
      this.feedbackService.addHotelFeedback(feedbackDTO).subscribe({
        next: (value) => {
          console.log(value);
          this.initializeFeedbacks();
        },
      })
    } else {
      this.errorMessages.pop();
      this.errorMessages.push("Trebuie sa completezi campul!");
    }
  }
}
