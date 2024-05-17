import { HotelInterface } from "./hotel.model";
import { User } from "./user.mode";

export interface FeedbackInterface {
    id: number,
    user: User,
    hotel: HotelInterface,
    feedback: string
}

export interface FeedbackDTO {
    userID: number,
    hotelID: number,
    feedback: string
}