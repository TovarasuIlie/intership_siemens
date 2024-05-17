import { HotelInterface } from "./hotel.model";

export interface RoomInterface  {
    id: number,
    roomNumber: number,
    type: number,
    available: boolean,
    price: number,
    hotel: HotelInterface
}