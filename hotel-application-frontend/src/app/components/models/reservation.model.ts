import { HotelInterface } from "./hotel.model";
import { RoomInterface } from "./room.model";
import { User } from "./user.mode";

export interface ReservationInterface {
    id: number,
    user: User,
    room: RoomInterface,
    hotel: HotelInterface
}