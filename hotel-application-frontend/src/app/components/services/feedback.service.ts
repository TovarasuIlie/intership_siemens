import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FeedbackDTO, FeedbackInterface } from '../models/feedback.model';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private http: HttpClient) { }

  getAllFeedbacks(hotelID: number) {
    return this.http.get<FeedbackInterface[]>(environment.apiUrl + "api/feedbacks/get-hotel-feedback/" + hotelID);
  }

  addHotelFeedback(feedback: FeedbackDTO) {
    return this.http.post(environment.apiUrl + "api/feedbacks/add-hotel-feedback", feedback);
  }
}
