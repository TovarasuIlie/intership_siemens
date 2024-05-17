import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../shared-components/header/header.component';
import { HotelInterface } from '../models/hotel.model';
import { HotelService } from '../services/hotel.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-hotel-list-page',
  standalone: true,
  imports: [HeaderComponent, CommonModule],
  templateUrl: './hotel-list-page.component.html',
  styleUrl: './hotel-list-page.component.css'
})
export class HotelListPageComponent implements OnInit {
  hotels: HotelInterface[] = [];

  constructor(private hotelService: HotelService) {}

  ngOnInit(): void {
    this.initializeHotels();
  }

  initializeHotels() {
    this.hotelService.getAllHotels().subscribe({
      next: (value) => {
        this.hotels = value;
      },
    })
  }
}
